package com.belema.listofkotlindevelopers.repo

import com.belema.listofkotlindevelopers.local.AppDatabase
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.remote.source.RemoteSource
import javax.inject.Inject

/**
 * Created by Belema Ogan on 2/2/21.
 */
class MyRepository @Inject constructor(private val networkSource: RemoteSource,
                                       private val databaseSource: AppDatabase) {

    suspend fun insertItems(items: List<ItemEntity>) = databaseSource.itemDao().insertItems(items)

    suspend fun getUserListRemote() = networkSource.fetchUserList()

    suspend fun getItemsLocal() = databaseSource.itemDao().items()

    suspend fun insertUser(user: UserEntity) = databaseSource.userDao().insertUser(user)

    suspend fun getUserRemote(login: String) = networkSource.fetchUser(login)

    suspend fun getUserLocal(login: String) = databaseSource.userDao().user(login)
}