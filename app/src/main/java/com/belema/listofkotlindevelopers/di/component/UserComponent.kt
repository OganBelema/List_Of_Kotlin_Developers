package com.belema.listofkotlindevelopers.di.component

import com.belema.listofkotlindevelopers.di.scope.FragmentScope
import com.belema.listofkotlindevelopers.ui.UserFragment
import com.belema.listofkotlindevelopers.viewmodel.UserViewModelFactory
import dagger.Component

/**
 * Created by Belema Ogan on 2/3/21.
 */
@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface UserComponent {
    fun inject(userFragment: UserFragment)
    fun getViewModelFactory(): UserViewModelFactory
}