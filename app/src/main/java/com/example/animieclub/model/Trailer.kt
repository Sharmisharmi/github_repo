package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Trailer {


    @SerializedName("youtube_id")
    @Expose
     val youtubeId: String? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

    @SerializedName("embed_url")
    @Expose
     val embedUrl: String? = null

    @SerializedName("images")
    @Expose
     val images: Images_1? = null
}
