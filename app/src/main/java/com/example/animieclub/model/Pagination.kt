package com.example.animieclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Pagination {
    @SerializedName("last_visible_page")
    @Expose
     val lastVisiblePage: Int? = null

    @SerializedName("has_next_page")
    @Expose
     val hasNextPage: Boolean? = null

    @SerializedName("current_page")
    @Expose
     val currentPage: Int? = null

    @SerializedName("items")
    @Expose
     val items: Items? = null
}
