package com.example.testing.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.UsersDB
import com.example.testing.model.UsersRepositoryImp
import com.example.testing.network.CheckConnection
import com.example.testing.network.dto.Data
import dagger.hilt.android.lifecycle.HiltViewModel
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
) :
    ViewModel() {

    private val _users = MutableStateFlow<List<Data>>(emptyList())
    val users = _users.asStateFlow()

    private val _showErrorToast = Channel<Boolean>()
    val showErrorToast = _showErrorToast.receiveAsFlow()

    init {
        viewModelScope.launch {
            usersRepositoryImp.getUsers().collectLatest { result ->
                when (result) {
                    is CheckConnection.Error -> {
                        _showErrorToast.send(true)
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