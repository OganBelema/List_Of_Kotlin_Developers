package com.belema.listofkotlindevelopers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.belema.listofkotlindevelopers.mapper.ItemMapper
import com.belema.listofkotlindevelopers.repo.MyRepository
import javax.inject.Inject

/**
 * Created by Belema Ogan on 2/3/21.
 */
class UserListViewModelFactory @Inject constructor(
    private val repository: MyRepository,
    private val itemMapper: ItemMapper): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(repository, itemMapper) as T
    }
}