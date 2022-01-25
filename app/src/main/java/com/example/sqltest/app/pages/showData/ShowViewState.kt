package com.example.sqltest.app.pages.showData

import com.example.sqltest.base.entity.Entity

data class ShowViewState(
    val loading: Boolean,
    val success: Boolean,
    val error: Boolean,
    val entity: Entity? = null
)