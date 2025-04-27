package com.example.ghithubrepo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class RepositorySearcGetData {

    @SerializedName("total_count")
    @Expose
     val totalCount: Int? = null

    @SerializedName("incomplete_results")
    @Expose
     val incompleteResults: Boolean? = null

    @SerializedName("items")
    @Expose
     val items: List<ItemList>? = null
}