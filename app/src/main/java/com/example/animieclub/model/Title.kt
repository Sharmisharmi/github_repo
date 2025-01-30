package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Title {

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("title")
    @Expose
     val title: String? = null
}
