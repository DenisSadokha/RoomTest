package com.example.sqltest.app.utils

import android.content.Context
import com.example.sqltest.App
import com.example.sqltest.app.di.AppComponent


val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }


