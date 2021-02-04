package com.belema.listofkotlindevelopers.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Belema Ogan on 2/4/21.
 */
@Entity(tableName = "item")
data class ItemEntity (
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    @PrimaryKey
    @NonNull
    val id: Int,
    val login: String
)