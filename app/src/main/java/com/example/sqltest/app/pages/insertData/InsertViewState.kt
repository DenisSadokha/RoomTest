package com.example.sqltest.app.pages.insertData

import com.example.sqltest.base.entity.Entity

data class InsertViewState(
    val loading: Boolean,
    val success: Boolean,
    val error: Boolean,
    val entity: Entity? = null
)