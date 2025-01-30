package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Items {

    @SerializedName("count")
    @Expose
     val count: Int? = null

    @SerializedName("total")
    @Expose
     val total: Int? = null

    @SerializedName("per_page")
    @Expose
     val perPage: Int? = null
}