package com.example.animieclub.model.animie_details_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class BroadcastDetailData {

    @SerializedName("day")
    @Expose
     val day: String? = null

    @SerializedName("time")
    @Expose
     val time: String? = null

    @SerializedName("timezone")
    @Expose
     val timezone: String? = null

    @SerializedName("string")
    @Expose
     val string: String? = null
}
