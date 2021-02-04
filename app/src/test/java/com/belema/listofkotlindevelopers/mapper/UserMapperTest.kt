package com.belema.listofkotlindevelopers.mapper

import com.belema.listofkotlindevelopers.factory.UserFactory
import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.remote.model.User
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Belema Ogan on 2/4/21.
 */
@RunWith(JUnit4::class)
class UserMapperTest {

    private val userMapper = UserMapper()

    @Test
    fun testFromEntity_returnsUserObjectWithSameProperties() {
        val userEntity = UserFactory.makeUserEntity()
        val user = userMapper.fromEntity(userEntity)

        //check that mapper returned User object
        assertThat(user, instanceOf(User::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(userEntity, user)
    }

    @Test
    fun testToEntity_returnsUserEntityObjectWithSameProperties() {
        val user = UserFactory.makeUser()
        val userEntity = userMapper.toEntity(user)

        //check that mapper returned UserEntity object
        assertThat(userEntity, instanceOf(UserEntity::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(userEntity, user)
    }

    @Test
    fun testFromEntityList_returnsListOfUserObjectsWithSameProperties() {
        val userEntities = listOf(UserFactory.makeUserEntity())
        val users = userMapper.fromEntityList(userEntities)

        //check that mapper returned list of User object
        assertThat(users?.get(0), instanceOf(User::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(userEntities[0], users?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfUserEntityObjectsWithSameProperties() {
        val users = listOf(UserFactory.makeUser())
        val userEntities = userMapper.toEntityList(users)

        //check that mapper returned list of UserEntity object
        assertThat(userEntities?.get(0), instanceOf(UserEntity::class.java))

        //check that mapper mapped the properties correctly
        assertEqualData(userEntities?.get(0), users[0])
    }

    private fun assertEqualData(entity: UserEntity?, model: User?) {
        assertEquals(entity?.id, model?.id)
        assertEquals(entity?.login, model?.login)
        assertEquals(entity?.name, model?.name)
        assertEquals(entity?.email, model?.email)
        assertEquals(entity?.avatarUrl, model?.avatarUrl)
        assertEquals(entity?.company, model?.company)
        assertEquals(entity?.followers, model?.followers)
        assertEquals(entity?.publicRepos, model?.publicRepos)
    }
}