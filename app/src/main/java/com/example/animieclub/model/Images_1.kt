package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Images_1 {


    @SerializedName("image_url")
    @Expose
     val imageUrl: String? = null

    @SerializedName("small_image_url")
    @Expose
     val smallImageUrl: String? = null

    @SerializedName("medium_image_url")
    @Expose
     val mediumImageUrl: String? = null

    @SerializedName("large_image_url")
    @Expose
     val largeImageUrl: String? = null

    @SerializedName("maximum_image_url")
    @Expose
     val maximumImageUrl: String? = null
}