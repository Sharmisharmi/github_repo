package com.example.ghithubrepo.view_model

import android.app.Application
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.ghithubrepo.model.RepositoryDeatialGetData
import com.example.ghithubrepo.model.RepositoryGetData
import com.example.ghithubrepo.model.RepositorySearcGetData
import com.example.ghithubrepo.repository.Repository
import com.facebook.shimmer.ShimmerFrameLayout

class GhithubViewModell(application: Application) : AndroidViewModel(application) {
    private val gitHubRepository = Repository(application)

    private var getRepositoryList: MutableLiveData<List<RepositoryGetData>> = MutableLiveData()

    fun getRepositories(
        email: String,
        shimmerViewContainer: ShimmerFrameLayout,
        rvRepos: RecyclerView,
        noDataImage: ImageView
    ): MutableLiveData<List<RepositoryGetData>> {
        getRepositoryList = gitHubRepository!!.requestRepositoryList(email,shimmerViewContainer,rvRepos,noDataImage)
        return getRepositoryList
    }


    private var searchRepositoryList: MutableLiveData<RepositorySearcGetData> = MutableLiveData()

    fun searchRepositories(
        query: String,
        shimmerViewContainer: ShimmerFrameLayout,
        rvRepos: RecyclerView,
        noDataImage: ImageView
    ): MutableLiveData<RepositorySearcGetData> {
        searchRepositoryList = gitHubRepository!!.requestSearchRepositoryList(query,shimmerViewContainer,rvRepos,noDataImage)
        return searchRepositoryList
    }

    private var getRepositoryDetail: MutableLiveData<RepositoryDeatialGetData> = MutableLiveData()

    fun getRepositoryDetail(
        owner: String,
        repo: String,
        shimmerViewContainer: ShimmerFrameLayout,
        main: LinearLayout,
        noDataImage: ImageView
    ): MutableLiveData<RepositoryDeatialGetData> {
        getRepositoryDetail = gitHubRepository!!.requestRepositoryDetails(owner,repo,shimmerViewContainer,main,noDataImage)
        return getRepositoryDetail
    }


}