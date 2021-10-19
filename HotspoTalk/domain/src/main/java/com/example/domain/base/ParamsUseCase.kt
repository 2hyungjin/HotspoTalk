package com.example.domain.base

abstract class ParamsUseCase<P, out T> {
    abstract suspend fun buildUseCase(params: P): T
}