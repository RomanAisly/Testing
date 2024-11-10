package com.example.testing.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.data.remote.CheckConnection
import com.example.testing.domain.model.Users
import com.example.testing.domain.model.UsersRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val usersRepositoryImp: UsersRepositoryImp
) : ViewModel() {

    private val _users = MutableStateFlow<List<Users>>(emptyList())
    val users = _users.asStateFlow()

    private val _showErrorToast = Channel<Boolean>()
    val showErrorToast = _showErrorToast.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepositoryImp.getUsers(
                page = 1,
                forceFetch = false
            ).collectLatest { result ->
                when (result) {
                    is CheckConnection.Error -> {
                        _showErrorToast.send(true)
                    }

                    is CheckConnection.Loading -> {
                        _showErrorToast.send(false)
                    }

                    is CheckConnection.Success -> {
                        result.data?.let { users ->
                            _users.update { users }
                        }
                    }
                }
            }
        }
    }




}