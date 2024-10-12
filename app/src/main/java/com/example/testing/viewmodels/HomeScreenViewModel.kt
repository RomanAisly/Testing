package com.example.testing.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.network.RetrofitClient
import com.example.testing.network.dto.Data
import com.example.testing.network.dto.UsersDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow<List<Data>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val users = RetrofitClient.retrofitService.getUsers(1)
            _state.value = users[0].data
        }
    }
}