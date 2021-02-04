package com.belema.listofkotlindevelopers.di.module

import android.content.Context
import com.belema.listofkotlindevelopers.di.qualifier.ApplicationContext
import com.belema.listofkotlindevelopers.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }
}