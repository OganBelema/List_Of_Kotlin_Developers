package com.belema.listofkotlindevelopers.viewmodel

import com.belema.listofkotlindevelopers.factory.UserFactory
import com.belema.listofkotlindevelopers.mapper.UserMapper
import com.belema.listofkotlindevelopers.repo.MyRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Belema Ogan on 2/4/21.
 */
class UserViewModelTest {

    private lateinit var userViewModel: UserViewModel

    private lateinit var userMapper: UserMapper

    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        repository = Mockito.mock(MyRepository::class.java)

        userMapper = Mockito.mock(UserMapper::class.java)

        userViewModel = UserViewModel(repository, userMapper)
    }

    @Test
    fun mapUserToUserEntity_callsUserMapper_toEntity(){
        val user = UserFactory.makeUser()

        userViewModel.mapUserToUserEntity(user)

        Mockito.verify(userMapper).toEntity(user)
    }

}