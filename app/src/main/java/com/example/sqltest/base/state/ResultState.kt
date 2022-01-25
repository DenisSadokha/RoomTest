package com.example.sqltest.base.state

import com.example.sqltest.base.entity.Entity

class ResultState<T : Entity> (
val state: State,
val entity: T?
)

sealed class State
data class LoadingState(val loading: Boolean) : State()
data class SuccessState(val code: Int) : State()
data class ErrorState(val errorCode: Int) : State()
