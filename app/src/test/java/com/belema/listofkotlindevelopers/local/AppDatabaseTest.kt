package com.belema.listofkotlindevelopers.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.belema.listofkotlindevelopers.factory.ItemFactory
import com.belema.listofkotlindevelopers.factory.UserFactory
import com.belema.listofkotlindevelopers.local.dao.ItemDao
import com.belema.listofkotlindevelopers.local.dao.UserDao
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.local.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Belema Ogan on 2/4/21.
 */
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var appDatabase: AppDatabase //the db instance
    private lateinit var itemDao: ItemDao //the item dao
    private lateinit var userDao: UserDao //the user dao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()

        itemDao = appDatabase.itemDao()

        userDao = appDatabase.userDao()
    }

    @Test
    fun testItemDaoOperations() = runBlocking {
        //assertThat item table has no data
        assertEquals(emptyList<ItemEntity>(), itemDao.items())

        //create sample itemEntity
        val itemEntity = ItemFactory.makeItemEntity()

        //create ItemEntity list and insert sample
        val itemEntities = listOf(itemEntity)

        //insert sample ItemEntity list
        itemDao.insertItems(itemEntities)

        //fetch saved entity list from DB
        val itemEntitiesFromDb = itemDao.items()

        assertEquals(itemEntity, itemEntitiesFromDb[0])

    }

    @Test
    fun testUserDaoOperations() = runBlocking {
        //assertThat user table has no data
        assertEquals(emptyList<UserEntity>(), userDao.users())

        //create sample userEntities
        val userEntity1 = UserFactory.makeUserEntity()
        val userEntity2 = UserFactory.makeUserEntity()

        //insert sample UserEntity
        userDao.insertUser(userEntity1)

        //fetch saved entity list from DB
        val userEntitiesFromDb = userDao.users()

        //confirm that fetched data was inserted data
        assertEquals(userEntity1, userEntitiesFromDb[0])

        //insert second userEntity sample
        userDao.insertUser(userEntity2)

        //fetch saved entity with login from DB
        val userEntity1FromDb = userDao.user(userEntity1.login)
        val userEntity2FromDb = userDao.user(userEntity2.login)

        //confirm that data is fetched has login requested
        assertEquals(userEntity1.login, userEntity1FromDb.login)
        assertEquals(userEntity2.login, userEntity2FromDb.login)

        //confirm that data saved is data fetched
        assertEquals(userEntity1, userEntity1FromDb)
        assertEquals(userEntity2, userEntity2FromDb)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }


}