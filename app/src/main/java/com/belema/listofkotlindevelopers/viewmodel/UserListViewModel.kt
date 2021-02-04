package com.belema.listofkotlindevelopers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belema.listofkotlindevelopers.local.entity.ItemEntity
import com.belema.listofkotlindevelopers.mapper.ItemMapper
import com.belema.listofkotlindevelopers.remote.model.Item
import com.belema.listofkotlindevelopers.remote.model.UserListApiResponse
import com.belema.listofkotlindevelopers.repo.MyRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

/**
 * Created by Belema Ogan on 2/2/21.
 */
class UserListViewModel(private val repository: MyRepository,
                        private val itemMapper: ItemMapper): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _data = MutableLiveData<List<ItemEntity>>()
    val data: LiveData<List<ItemEntity>> = _data

    fun getData(isOnline: Boolean) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                if (isOnline){
                    //get data from remote source
                    val networkResponse = makeRemoteRequest()
                    val items = networkResponse.items

                    //map list of items to list of ItemEntities
                    val itemEntities = mapItemsToItemEntity(items)

                    //save to database
                    saveItemsToDatabase(itemEntities)
                }

                //fetch from database
                fetchUsersFromDatabase()

                _isLoading.value = false
            } catch (exception: Exception){
                _isLoading.value = false
                _error.value = exception
                Timber.d(exception)
            }

        }
    }

    fun mapItemsToItemEntity(items: List<Item>): List<ItemEntity>? {
        return itemMapper.toEntityList(items)
    }

    private suspend fun makeRemoteRequest(): UserListApiResponse {
        return repository.getUserListRemote()
    }

    private suspend fun saveItemsToDatabase(itemEntities: List<ItemEntity>?) {
        if (itemEntities != null) {
            repository.insertItems(itemEntities)
        }
    }

    private suspend fun fetchUsersFromDatabase() {
        val users = repository.getItemsLocal()
        _data.postValue(users)
    }

}