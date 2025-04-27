package com.example.ghithubrepo.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.ghithubrepo.api.APInterface
import com.example.ghithubrepo.api.ApiClient
import com.example.ghithubrepo.model.ItemList
import com.example.ghithubrepo.model.RepositoryDeatialGetData
import com.example.ghithubrepo.model.RepositoryGetData
import com.example.ghithubrepo.model.RepositorySearcGetData
import com.example.ghithubrepo.room_db.AppDatabase
import com.example.ghithubrepo.room_db.RepoDetailEntity
import com.example.ghithubrepo.room_db.RepoEntity
import com.example.ghithubrepo.room_db.RepoSearchEntity
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val context: Context) {

    val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)



    fun requestRepositoryList(
        email: String,
        shimmerViewContainer: ShimmerFrameLayout,
        rvRepos: RecyclerView,
        noDataImage: ImageView
    ): MutableLiveData<List<RepositoryGetData>> {
        val mutableLiveData: MutableLiveData<List<RepositoryGetData>>  = MutableLiveData()
        apiService?.getrepositoriesList(email)?.enqueue(object :
            Callback<List<RepositoryGetData>?> {
            override fun onResponse(call: Call<List<RepositoryGetData>?>?, response: Response<List<RepositoryGetData>?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )
                    response.body()?.let { repoList ->
                        saveRepositoriesToRoom(repoList)
                    }
                    Log.d("response_raw", "onResponse: "+response.raw())
                }else{
                    noImageVisible(shimmerViewContainer,rvRepos,noDataImage)
                    Log.d("response_raw", "onError: "+response.raw())
                }
            }
            public override fun onFailure(call: Call<List<RepositoryGetData>?>, t: Throwable?) {
                Log.d("response_raw", "onFailure: "+t.toString())
                noImageVisible(shimmerViewContainer,rvRepos,noDataImage)

            }
        })

        return mutableLiveData
    }


    fun requestSearchRepositoryList(
        query: String,
        shimmerViewContainer: ShimmerFrameLayout,
        rvRepos: RecyclerView,
        noDataImage: ImageView
    ): MutableLiveData<RepositorySearcGetData> {
        val mutableLiveData: MutableLiveData<RepositorySearcGetData>  = MutableLiveData()


        apiService?.getserachrepositoriesList(query)?.enqueue(object :
            Callback<RepositorySearcGetData?> {
            override fun onResponse(call: Call<RepositorySearcGetData?>?, response: Response<RepositorySearcGetData?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )
                    response.body()?.let { repoList ->
                        saveSearchRepositoriesToRoom(repoList.items,query)
                    }
                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{
                    noImageVisible(shimmerViewContainer,rvRepos,noDataImage)
                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<RepositorySearcGetData?>, t: Throwable?) {
                Log.d("response_raw", "onFailure: "+t.toString())
                noImageVisible(shimmerViewContainer,rvRepos,noDataImage)
            }
        })

        return mutableLiveData
    }


    fun requestRepositoryDetails(
        owner: String,
        repo: String,
        shimmerViewContainer: ShimmerFrameLayout,
        main: LinearLayout,
        noDataImage: ImageView
    ): MutableLiveData<RepositoryDeatialGetData> {
        val mutableLiveData: MutableLiveData<RepositoryDeatialGetData>  = MutableLiveData()


        apiService?.getrepositoryDetails(owner,repo)?.enqueue(object :
            Callback<RepositoryDeatialGetData?> {
            override fun onResponse(call: Call<RepositoryDeatialGetData?>?, response: Response<RepositoryDeatialGetData?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )
                    response.body()?.let { repoList ->
                        saveRepositoryDetailToRoom(repoList)
                    }
                    Log.d("response_raw", "onResponse: "+response.raw())


                }else{

                    noDetailImage(shimmerViewContainer,main,noDataImage)
                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<RepositoryDeatialGetData?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())
                noDetailImage(shimmerViewContainer,main,noDataImage)
            }
        })

        return mutableLiveData
    }



    // Offline Cache

    val repositoryDao = AppDatabase.getDatabase(context).repoDao()
    val repositoryDetailDao = AppDatabase.getDatabase(context).repoDetailDao()
    val repositorySearchDao = AppDatabase.getDatabase(context).repoSearchDao()


    private fun saveRepositoriesToRoom(repoList: List<RepositoryGetData>) {
        val repoEntities = repoList.map {
            RepoEntity(
                id = it.id ?: 0,
                name = it.name,
                owner = it.owner?.login.toString(),
                description = it.description?.toString(),
                language = it.language.toString(),
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount

                )
        }

        // Insert into Room database using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            repositoryDao.insertRepos(repoEntities)
        }
    }

    private fun saveSearchRepositoriesToRoom(repoList: List<ItemList>?, query: String) {
        val repoEntities = repoList?.map {
            RepoSearchEntity(
                id = it.id ?: 0,
                query = query,
                name = it.name,
                owner = it.owner?.login.toString(),
                description = it.description?.toString(),
                language = it.language.toString(),
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount

            )
        }

        // Insert into Room database using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            repositorySearchDao.insertSearchRepos(repoEntities)
        }
    }

    private fun saveRepositoryDetailToRoom(repoDetail: RepositoryDeatialGetData) {
        val repoEntity = RepoDetailEntity(
            owner = repoDetail.owner?.login.toString(),
            name = repoDetail.name.toString(),
            description = repoDetail.description?.toString(),
            language = repoDetail.language.toString(),
            stargazersCount = repoDetail.stargazersCount,
            forksCount = repoDetail.forksCount,
            contributors = repoDetail.contributorsUrl.toString(),
            issuesCount = repoDetail.openIssuesCount,
            lastUpdated = repoDetail.updatedAt
        )

        // Insert into Room database using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            repositoryDetailDao.insertRepoDetail(listOf(repoEntity))  // Insert as a list, even if it contains one item
        }
    }



    fun noImageVisible(
        shimmerViewContainer: ShimmerFrameLayout,
        rvRepos: RecyclerView,
        noDataImage: ImageView
    ) {
        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
        rvRepos.visibility = View.GONE
        noDataImage.visibility = View.VISIBLE
    }

    fun noDetailImage(shimmerViewContainer: ShimmerFrameLayout, main: LinearLayout, noDataImage: ImageView) {

        shimmerViewContainer.stopShimmer()
        shimmerViewContainer.visibility = View.GONE
        main.visibility = View.GONE
        noDataImage.visibility = View.VISIBLE

    }



}