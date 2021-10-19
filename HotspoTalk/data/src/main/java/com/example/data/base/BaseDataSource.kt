package com.example.data.base

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource<SV> {

    protected abstract val service: SV

    fun <T> getResponse(response: Response<T>): T {
        error(response)
        return response.body()!!
    }

    private fun <T> error(response: Response<T>) {
        val error = response.errorBody()?.string()
        Log.d("BaseDataSource", error.toString())
        Throwable(error)
    }
}