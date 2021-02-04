package com.belema.listofkotlindevelopers.viewmodel

import com.belema.listofkotlindevelopers.factory.ItemFactory
import com.belema.listofkotlindevelopers.mapper.ItemMapper
import com.belema.listofkotlindevelopers.repo.MyRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Belema Ogan on 2/4/21.
 */
class UserListViewModelTest {

    private lateinit var userListViewModel: UserListViewModel

    private lateinit var itemMapper: ItemMapper

    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        repository = Mockito.mock(MyRepository::class.java)

        itemMapper = Mockito.mock(ItemMapper::class.java)

        userListViewModel = UserListViewModel(repository, itemMapper)
    }

    @Test
    fun mapItemsToItemEntity_callsItemMapper_toEntityList(){
        val items = listOf(ItemFactory.makeItem())

        userListViewModel.mapItemsToItemEntity(items)

        Mockito.verify(itemMapper).toEntityList(items)
    }
}