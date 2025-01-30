package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Prop {

    @SerializedName("from")
    @Expose
     val from: From? = null

    @SerializedName("to")
    @Expose
     val to: To? = null
}