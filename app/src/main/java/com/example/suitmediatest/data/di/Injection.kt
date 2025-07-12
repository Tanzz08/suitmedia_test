package com.example.suitmediatest.data.di

import android.content.Context
import com.example.suitmediatest.data.remote.ApiConfig
import com.example.suitmediatest.data.repository.MainRepository

object Injection {
    fun provideMainRepository(context: Context): MainRepository {
        val apiService = ApiConfig.getUserData()
        return MainRepository.getInstance(apiService, context)
    }
}