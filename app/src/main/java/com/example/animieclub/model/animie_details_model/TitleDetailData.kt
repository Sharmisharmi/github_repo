package com.example.animieclub.model.animie_details_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class TitleDetailData {

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("title")
    @Expose
     val title: String? = null
}