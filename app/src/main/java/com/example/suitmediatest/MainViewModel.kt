package com.example.suitmediatest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.repository.MainRepository
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.response.ResultState

class MainViewModel(private val repository: MainRepository): ViewModel() {
    val userData: LiveData<PagingData<DataItem>> =
        repository.getUserData().cachedIn(viewModelScope)

}