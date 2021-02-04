package com.belema.listofkotlindevelopers.di.component

import android.content.Context
import com.belema.listofkotlindevelopers.MyApp
import com.belema.listofkotlindevelopers.di.module.DatabaseModule
import com.belema.listofkotlindevelopers.di.module.NetworkModule
import com.belema.listofkotlindevelopers.di.qualifier.ApplicationContext
import com.belema.listofkotlindevelopers.di.scope.ApplicationScope
import com.belema.listofkotlindevelopers.local.AppDatabase
import com.belema.listofkotlindevelopers.remote.Api
import com.belema.listofkotlindevelopers.ui.MainActivity
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent{

    fun getApiInterface(): Api
    fun getDatabase(): AppDatabase
    fun inject(activity: MainActivity)
    fun injectApplication(app: MyApp)
    @ApplicationContext
    fun getContext(): Context

}