package com.example.animieclub.model.animie_details_model

import com.example.animieclub.model.Jpg
import com.example.animieclub.model.Webp
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ImagesDetailData {


    @SerializedName("jpg")
    @Expose
     val jpg: Jpg? = null

    @SerializedName("webp")
    @Expose
     val webp: Webp? = null

}
