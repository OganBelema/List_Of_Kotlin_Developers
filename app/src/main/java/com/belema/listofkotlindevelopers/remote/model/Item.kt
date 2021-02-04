package com.belema.listofkotlindevelopers.remote.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val id: Int,
    val login: String
)