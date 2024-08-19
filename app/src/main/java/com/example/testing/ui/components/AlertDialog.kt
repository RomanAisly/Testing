package com.example.testing.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialog() {
    var dialog by remember {
        mutableStateOf(false)
    }
    Button(onClick = { dialog = true }) {
        Text(
            text = "delete",
            fontSize = 20.sp
        )
    }
    if (dialog) {
        AlertDialog(
            onDismissRequest = { dialog = false },
            dismissButton = {
                Button(
                    onClick = { dialog = true },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "NO")
                }
            },
            confirmButton = {
                Button(
                    onClick = { dialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Red
                    )
                ) {
                    Text(
                        text = "Yes",
                        fontSize = 14.sp,
                    )
                }
            },
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "") },
            iconContentColor = Color.Red,
            containerColor = Color.DarkGray,
            title = { Text(text = "Confirm  your action") },
            titleContentColor = Color.Green,
            text = {
                Text(
                    text = "Are you sure you want to delete this item?",
                    fontSize = 20.sp
                )
            },
        )
    }
}