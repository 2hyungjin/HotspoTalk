package com.example.domain.base

abstract class BaseUseCse<out T> {
    abstract suspend fun buildUseCase(): T
}