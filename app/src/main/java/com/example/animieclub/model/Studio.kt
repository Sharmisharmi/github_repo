package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Studio {

    @SerializedName("mal_id")
    @Expose
     val malId: Int? = null

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

}
