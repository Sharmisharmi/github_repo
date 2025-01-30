package com.example.animieclub.view_model

import androidx.lifecycle.MutableLiveData
import com.example.animieclub.model.GetAnimieListData
import com.example.animieclub.model.GetAnimieListResponse
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsData
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsResponse
import com.example.animieclub.repository.Repository

class ViewModel {

    var animieRepository: Repository = Repository()

    private var animieList: MutableLiveData<GetAnimieListResponse> = MutableLiveData()

    fun getAnimieList(): MutableLiveData<GetAnimieListResponse> {
        animieList = animieRepository!!.requestAnimieList()
        return animieList
    }

     private var animieDetail: MutableLiveData<GetAnimieDetailsResponse> = MutableLiveData()

    fun getAnimieDetails(id:Int): MutableLiveData<GetAnimieDetailsResponse> {
        animieDetail = animieRepository!!.requestAnimieDetail(id)
        return animieDetail
    }


}