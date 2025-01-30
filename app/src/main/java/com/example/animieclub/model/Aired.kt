package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Aired {


    @SerializedName("from")
    @Expose
     val from: String? = null

    @SerializedName("to")
    @Expose
     val to: String? = null

    @SerializedName("prop")
    @Expose
     val prop: Prop? = null

    @SerializedName("string")
    @Expose
     val string: String? = null
}
