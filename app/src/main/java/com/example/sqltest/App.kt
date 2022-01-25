package com.example.sqltest

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.sqltest.app.di.AppComponent
import com.example.sqltest.app.di.DaggerAppComponent
import com.example.sqltest.app.di.modules.AppModule
import com.example.sqltest.localStorage.db.MusicalSchoolDataBase

class App : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().context(this).build()
        val appDb = Room.databaseBuilder(this, MusicalSchoolDataBase::class.java, "school_database"
            ).addMigrations()
            .build()
        

    }


    companion object {


        lateinit var instance: App
            private set
    }

}


