package com.example.sqltest.app.di

import android.content.Context
import com.example.sqltest.app.di.modules.AppModule
import com.example.sqltest.app.di.modules.StorageModule
import com.example.sqltest.app.pages.insertData.InsertActivity
import com.example.sqltest.app.pages.showData.ShowInfoActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, StorageModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }


    fun inject(insertActivity: InsertActivity)

    fun inject(showInfoActivity: ShowInfoActivity)

}