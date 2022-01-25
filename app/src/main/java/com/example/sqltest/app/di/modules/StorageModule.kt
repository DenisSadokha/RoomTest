package com.example.sqltest.app.di.modules

import android.content.Context
import androidx.room.Room
import com.example.sqltest.localStorage.dao.MainDao
import com.example.sqltest.localStorage.db.MusicalSchoolDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideMusicalSchoolDataBase(context: Context): MusicalSchoolDataBase {
        return Room.databaseBuilder(
            context,
            MusicalSchoolDataBase::class.java,
            "school_database"
        ).addCallback(MusicalSchoolDataBase.DB_CALLBACK)
            .build()
    }


    @Provides
    fun provideMainDao(db: MusicalSchoolDataBase): MainDao {
        return db.musicalSchoolDao()
    }


}