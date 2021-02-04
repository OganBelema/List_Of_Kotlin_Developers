package com.belema.listofkotlindevelopers.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Belema Ogan on 2/2/21.
 */
@Entity(tableName = "user")
data class UserEntity (
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    val company: String?,
    val email: String?,
    val followers: Int?,
    @PrimaryKey
    @NonNull
    val id: Int,
    val login: String,
    val name: String?,
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int?
)