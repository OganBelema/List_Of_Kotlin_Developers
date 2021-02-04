package com.belema.listofkotlindevelopers.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.belema.listofkotlindevelopers.local.dao.ItemDao
import com.belema.listofkotlindevelopers.local.dao.UserDao
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.local.entity.UserEntity

/**
 * Created by Belema Ogan on 2/1/21.
 */
@Database(entities = [UserEntity::class, ItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
}