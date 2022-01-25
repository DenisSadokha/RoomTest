package com.example.sqltest.app.di.modules

import android.content.Context
import com.example.sqltest.data.features.musicSchoolInfo.datasource.MusicSchoolDataSource
import com.example.sqltest.localStorage.dao.MainDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideMusicSchoolDataSource(dao: MainDao): MusicSchoolDataSource {
        return MusicSchoolDataSource(dao)
    }








}