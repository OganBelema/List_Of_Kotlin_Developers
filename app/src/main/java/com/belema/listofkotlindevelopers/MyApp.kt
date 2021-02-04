package com.belema.listofkotlindevelopers

import android.app.Application
import com.belema.listofkotlindevelopers.di.component.ApplicationComponent
import com.belema.listofkotlindevelopers.di.component.DaggerApplicationComponent
import com.belema.listofkotlindevelopers.di.module.ApplicationContextModule
import timber.log.Timber

/**
 * Created by Belema Ogan on 2/1/21.
 */
class MyApp: Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
        private var instance: MyApp? = null

        fun getInstance(): MyApp {
            return this.instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        instance = this
        applicationComponent = initDagger(this)

    }

    private fun initDagger(app: MyApp): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationContextModule(ApplicationContextModule(app))
            .build()

}