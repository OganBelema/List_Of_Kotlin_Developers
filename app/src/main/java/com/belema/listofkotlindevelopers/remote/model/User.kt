package com.belema.listofkotlindevelopers.remote.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val email: String?,
    val company: String?,
    val followers: Int?,
    val id: Int,
    val login: String,
    val name: String?,
    @SerializedName("public_repos")
    val publicRepos: Int?
)