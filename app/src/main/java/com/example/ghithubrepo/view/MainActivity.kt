package com.example.ghithubrepo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghithubrepo.Utils
import com.example.ghithubrepo.adapter.RepoAdapter
import com.example.ghithubrepo.databinding.ActivityMainBinding
import com.example.ghithubrepo.model.ItemList
import com.example.ghithubrepo.model.OwnerGetData
import com.example.ghithubrepo.model.RepositoryGetData
import com.example.ghithubrepo.model.RepositorySearcGetData
import com.example.ghithubrepo.repository.Repository
import com.example.ghithubrepo.view_model.GhithubViewModell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GhithubViewModell
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var repository: Repository
    private var username: String? = null
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val email = sharedPref.getString("EMAIL", null)
        Log.d("email", "onCreate: $email")


        viewModel = ViewModelProvider(this)[GhithubViewModell::class.java]
        repository = Repository(this)

        binding.shimmerViewContainer.startShimmer()
        setupRecyclerView()




        if (!email.isNullOrEmpty()) {
            username = email
            username?.let { user ->
                if (Utils.isNetworkAvailable(this)) {
                    fetchRepositories(user)
                } else {
                    Toast.makeText(this, "No Internet. Showing offline data.", Toast.LENGTH_SHORT).show()
                    loadOfflineData()
                }
            }
        } else {
            Toast.makeText(this, "No access token; please log in.", Toast.LENGTH_LONG).show()
            finish()
        }



        binding.searchImage.setOnClickListener {
            binding.eldersSearchView.visibility = if (binding.eldersSearchView.isVisible) View.GONE else View.VISIBLE
        }



        binding.eldersSearchView.setOnSearchTextChangeListener { chars ->

            if (Utils.isNetworkAvailable(this)) {
                if (!chars.isNullOrEmpty()) {
                    fetchSearchRepositories(chars.toString())
                } else {
                    username?.let { fetchRepositories(it) }
                }
            }else{
                Toast.makeText(this, "No Internet. Showing offline data.", Toast.LENGTH_SHORT).show()
                if (!chars.isNullOrEmpty()) {
                    loadOfflineSearchData(chars.toString())
                } else {
                   loadOfflineData()
                }
            }

        }



        binding.signOutImage.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }


    }

    private fun setupRecyclerView() {
        repoAdapter = RepoAdapter(this, emptyList(), emptyList(), "repos")
        binding.rvRepos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = repoAdapter
        }
    }

    private fun fetchRepositories(userName: String) {
        viewModel.getRepositories(userName,binding.shimmerViewContainer,binding.rvRepos,binding.noDataImage).observe(this) { repos ->
            if (repos.isNotEmpty()) {
                repoAdapter = RepoAdapter(this, repos, emptyList(), "repos")
                binding.rvRepos.adapter = repoAdapter
               recyclerVisible()

            } else {
                noImageVisible()
            }
        }
    }

    private fun fetchSearchRepositories(query: String) {
        viewModel.searchRepositories(query,binding.shimmerViewContainer,binding.rvRepos,binding.noDataImage).observe(this) { repos: RepositorySearcGetData ->
            if (!repos.items.isNullOrEmpty()) {
                repoAdapter = RepoAdapter(this, emptyList(), repos.items, "search")
                binding.rvRepos.adapter = repoAdapter
                recyclerVisible()
            } else {
                noImageVisible()
            }
        }
    }

    private fun loadOfflineData() {
        CoroutineScope(Dispatchers.IO).launch {
            val offlineRepos = repository.repositoryDao.getAllRepos()
            withContext(Dispatchers.Main) {
                if (offlineRepos.isNotEmpty()) {
                    val repoList = offlineRepos.map {
                        RepositoryGetData().apply {
                            id = it.id
                            name = it.name
                            owner = OwnerGetData().apply { login = it.owner }
                            description = it.description
                            language = it.language
                            stargazersCount = it.stargazersCount
                            forksCount = it.forksCount
                        }
                    }
                    repoAdapter = RepoAdapter(this@MainActivity, repoList, emptyList(), "repos")
                    binding.rvRepos.adapter = repoAdapter
                    recyclerVisible()


                } else {
                    noImageVisible()

                }
            }
        }
    }



    private fun loadOfflineSearchData(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val offlineRepos = repository.repositorySearchDao.getSearchRepos(query)
            withContext(Dispatchers.Main) {
                if (offlineRepos.isNotEmpty()) {
                    val repoList = offlineRepos.map {
                        ItemList().apply {
                            id = it.id
                            name = it.name
                            owner = OwnerGetData().apply { login = it.owner }
                            description = it.description
                            language = it.language
                            stargazersCount = it.stargazersCount
                            forksCount = it.forksCount
                        }
                    }
                    repoAdapter = RepoAdapter(this@MainActivity, emptyList(), repoList, "search")
                    binding.rvRepos.adapter = repoAdapter
                  recyclerVisible()
                } else {
                    noImageVisible()
                }
            }
        }
    }



    private fun recyclerVisible() {
        binding.rvRepos.visibility = View.VISIBLE
        binding.noDataImage.visibility = View.GONE
        layoutSetup()
    }

    private fun noImageVisible(){
        binding.rvRepos.visibility = View.GONE
        binding.noDataImage.visibility = View.VISIBLE
        layoutSetup()
    }


   fun layoutSetup(){
        // When data is loaded
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
    }



    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

}
