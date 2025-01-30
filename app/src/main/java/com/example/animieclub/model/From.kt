package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class From {
    @SerializedName("day")
    @Expose
     val day: Int? = null

    @SerializedName("month")
    @Expose
     val month: Int? = null

    @SerializedName("year")
    @Expose
     val year: Int? = null
}