package com.belema.listofkotlindevelopers.di.component

import com.belema.listofkotlindevelopers.di.scope.FragmentScope
import com.belema.listofkotlindevelopers.ui.UserListFragment
import com.belema.listofkotlindevelopers.viewmodel.UserListViewModelFactory
import dagger.Component

/**
 * Created by Belema Ogan on 2/3/21.
 */
@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface UserListComponent {
    fun inject(userListFragment: UserListFragment)
    fun getViewModelFactory(): UserListViewModelFactory
}