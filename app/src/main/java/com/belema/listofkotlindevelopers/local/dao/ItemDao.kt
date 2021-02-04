package com.belema.listofkotlindevelopers.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.belema.listofkotlindevelopers.local.entity.ItemEntity

/**
 * Created by Belema Ogan on 2/4/21.
 */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(users: List<ItemEntity>)

    @Query("SELECT * FROM item")
    suspend fun items(): List<ItemEntity>
}