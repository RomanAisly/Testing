package com.example.testing.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testing.network.dto.Data
import com.example.testing.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = viewModel()) {

    val users by viewModel.users.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel.showErrorToast) {
        viewModel.showErrorToast.collect { show ->
            if (show) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (users.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(users.size) { index ->
                    UserItem(user = users[index])
                }
            }
        }
    }
}

@Composable
fun UserItem(modifier: Modifier = Modifier, user: Data) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = user.email)
        Text(text = user.first_name)
        Text(text = user.last_name)
        Text(text = user.avatar)
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {

}