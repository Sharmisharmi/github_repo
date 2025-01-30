package com.example.animieclub.api

import com.example.animieclub.model.GetAnimieListData
import com.example.animieclub.model.GetAnimieListResponse
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsData
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APInterface {

    @GET("top/anime")
    fun getAnimieList(): Call<GetAnimieListResponse?>?


    @GET("anime/{id}")
    fun getAnimieDetail(@Path("id") id: Int): Call<GetAnimieDetailsResponse?>?
}