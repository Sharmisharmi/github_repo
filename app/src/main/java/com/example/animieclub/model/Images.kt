package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Images {
    @SerializedName("jpg")
    @Expose
     val jpg: Jpg? = null

    @SerializedName("webp")
    @Expose
     val webp: Webp? = null
}
