package com.example.suitmediatest.data.repository


import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatest.data.remote.ApiService
import com.example.suitmediatest.data.response.DataItem


class MainRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
){

    fun getUserData(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(apiService: ApiService, context: Context) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, context)
            }.also { instance = it }
    }
}