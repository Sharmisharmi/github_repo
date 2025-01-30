package com.example.animieclub.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.animieclub.api.APInterface
import com.example.animieclub.api.ApiClient
import com.example.animieclub.model.GetAnimieListData
import com.example.animieclub.model.GetAnimieListResponse
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsData
import com.example.animieclub.model.animie_details_model.GetAnimieDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun requestAnimieList(): MutableLiveData<GetAnimieListResponse> {
        val mutableLiveData: MutableLiveData<GetAnimieListResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getAnimieList()?.enqueue(object :
            Callback<GetAnimieListResponse?> {
            override fun onResponse(call: Call<GetAnimieListResponse?>?, response: Response<GetAnimieListResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<GetAnimieListResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }


    fun requestAnimieDetail(id:Int): MutableLiveData<GetAnimieDetailsResponse> {
        val mutableLiveData: MutableLiveData<GetAnimieDetailsResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getAnimieDetail(id)?.enqueue(object :
            Callback<GetAnimieDetailsResponse?> {
            override fun onResponse(call: Call<GetAnimieDetailsResponse?>?, response: Response<GetAnimieDetailsResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("animie_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("animie_raw_err", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<GetAnimieDetailsResponse?>, t: Throwable?) {

                Log.d("animie_raw_fail", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

}