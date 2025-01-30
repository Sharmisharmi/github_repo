package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetAnimieListResponse {

    @SerializedName("pagination")
    @Expose
     val pagination: Pagination? = null

    @SerializedName("data")
    @Expose
     val data: List<GetAnimieListData>? = null
}