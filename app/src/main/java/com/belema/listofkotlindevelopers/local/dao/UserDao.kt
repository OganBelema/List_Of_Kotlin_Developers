package com.belema.listofkotlindevelopers.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.belema.listofkotlindevelopers.local.entity.UserEntity

/**
 * Created by Belema Ogan on 2/2/21.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun users(): List<UserEntity>

    @Query("SELECT * FROM user WHERE login = :login")
    suspend fun user(login: String): UserEntity
}