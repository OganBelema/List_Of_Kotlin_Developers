package com.belema.listofkotlindevelopers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.mapper.UserMapper
import com.belema.listofkotlindevelopers.remote.model.User
import com.belema.listofkotlindevelopers.repo.MyRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

/**
 * Created by Belema Ogan on 2/3/21.
 */
class UserViewModel(private val repository: MyRepository,
                    private val userMapper: UserMapper): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _data = MutableLiveData<UserEntity>()
    val data: LiveData<UserEntity> = _data

    fun getData(isOnline: Boolean, login: String) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                if (isOnline){
                    //get data from remote source
                    val userApiResponse = makeRemoteRequest(login)

                    //map userApiResponse to user
                    val user = mapUserToUserEntity(userApiResponse)

                    //save to database
                    saveUserToDatabase(user)
                }

                //fetch from database
                fetchUserFromDatabase(login)

                _isLoading.value = false
            } catch (exception: Exception){
                _isLoading.value = false
                _error.value = exception
                Timber.d(exception)
            }

        }
    }

    fun mapUserToUserEntity(user: User): UserEntity {
        return userMapper.toEntity(user)
    }

    private suspend fun makeRemoteRequest(login: String): User {
        return repository.getUserRemote(login)
    }

    private suspend fun saveUserToDatabase(user: UserEntity?) {
        if (user != null) {
            repository.insertUser(user)
        }
    }

    private suspend fun fetchUserFromDatabase(login: String) {
        val user = repository.getUserLocal(login)
        _data.postValue(user)
    }
}