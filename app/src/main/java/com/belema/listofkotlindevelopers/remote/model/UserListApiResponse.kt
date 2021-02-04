package com.belema.listofkotlindevelopers.remote.model

import com.google.gson.annotations.SerializedName

data class UserListApiResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)