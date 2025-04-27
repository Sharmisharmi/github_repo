package com.example.ghithubrepo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ghithubrepo.Utils
import com.example.ghithubrepo.databinding.ActivityRepositoryDetailBinding
import com.example.ghithubrepo.model.OwnerDetailGetData
import com.example.ghithubrepo.model.RepositoryDeatialGetData
import com.example.ghithubrepo.repository.Repository
import com.example.ghithubrepo.view_model.GhithubViewModell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityRepositoryDetailBinding
    private lateinit var viewModel: GhithubViewModell
    private lateinit var repository: Repository // Initialize repository class (for offline Room)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GhithubViewModell::class.java]
        repository = Repository(this)

        val getOwnerName = intent.getStringExtra("OWNER") ?: ""
        val getRepoName = intent.getStringExtra("REPO") ?: ""

        Log.d("getOwnerName + getRepoName", "onCreate: $getOwnerName:$getRepoName")

        if (Utils.isNetworkAvailable(this)) {
            if (getOwnerName.isNotEmpty() && getRepoName.isNotEmpty()) {
                // Fetch repository details from network
                viewModel.getRepositoryDetail(getOwnerName, getRepoName,binding.shimmerViewContainer,binding.main,binding.noDataImage).observe(this) { repoDetail ->
                    setUpUi(repoDetail)
                   layOutVisible()
                }
            } else {
                Toast.makeText(this, "Invalid repository details", Toast.LENGTH_LONG).show()
                noImageVisible()
                finish()
            }
        } else {
            Toast.makeText(this, "No Internet. Showing offline data.", Toast.LENGTH_SHORT).show()
            loadOfflineData(getOwnerName, getRepoName)
        }
    }

    private fun setUpUi(repoDetail: RepositoryDeatialGetData) {
        // Set the UI with the repository details
        binding.repoNameTv.text = repoDetail.name ?: "No Name"
        binding.ownerTv.text = repoDetail.owner?.login ?: "Unknown Owner"
        binding.descriptionTv.text = (repoDetail.description ?: "No Description").toString()
        binding.langTv.text = (repoDetail.language ?: "N/A").toString()
        binding.starTv.text = repoDetail.stargazersCount?.toString() ?: "0"
    }

    private fun loadOfflineData(ownerName: String, repoName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Get the repo details from Room based on owner and repo name
                val offlineRepo = repository.repositoryDetailDao.getRepoDetail(ownerName, repoName)
                withContext(Dispatchers.Main) {
                    if (offlineRepo != null) {
                        // Create a new instance of RepositoryDeatialGetData and set the values manually
                        val repoDetail = RepositoryDeatialGetData().apply {
                            // Set the fields manually (Not via constructor)
                            owner = OwnerDetailGetData().apply { login = offlineRepo.owner }// Correctly map the owner
                            name = offlineRepo.name
                            description = offlineRepo.description
                            language = offlineRepo.language
                            stargazersCount = offlineRepo.stargazersCount
                            forksCount = offlineRepo.forksCount
                        }

                        // Set the UI with offline data
                        layOutVisible()
                        setUpUi(repoDetail)
                    } else {
                      noImageVisible()
                        Toast.makeText(this@RepositoryDetailActivity, "No offline data available", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Handle any exception that might occur
                Log.e("RepositoryDetailActivity", "Error loading offline data", e)
                Toast.makeText(this@RepositoryDetailActivity, "Error loading offline data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun layOutVisible(){
        binding.main.visibility = View.VISIBLE
        binding.noDataImage.visibility = View.GONE
        shimmerSetup()
    }


    fun noImageVisible(){
        binding.main.visibility = View.GONE
        binding.noDataImage.visibility = View.VISIBLE
        shimmerSetup()
    }

    fun shimmerSetup(){
        // When data is loaded
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
