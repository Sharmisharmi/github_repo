package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Broadcast {


    @SerializedName("day")
    @Expose
     val day: Any? = null

    @SerializedName("time")
    @Expose
     val time: Any? = null

    @SerializedName("timezone")
    @Expose
     val timezone: Any? = null

    @SerializedName("string")
    @Expose
     val string: Any? = null
}
