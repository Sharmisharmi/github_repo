package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Webp {

    @SerializedName("image_url")
    @Expose
     val imageUrl: String? = null

    @SerializedName("small_image_url")
    @Expose
     val smallImageUrl: String? = null

    @SerializedName("large_image_url")
    @Expose
     val largeImageUrl: String? = null
}
