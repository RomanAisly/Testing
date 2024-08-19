package com.example.testing.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState

@Composable
fun CustomCheckBox() {

    var checked1 by remember { mutableStateOf(ToggleableState.Off) }
    var checked2 by remember { mutableStateOf(ToggleableState.Off) }
    var checked3 by remember { mutableStateOf(ToggleableState.Off) }

    Column(
        modifier = Modifier.wrapContentSize(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriStateCheckbox(
                state = checked1,
                onClick = {
                    if (checked1 == ToggleableState.Off) {
                        checked1 = ToggleableState.On
                    } else {
                        checked1 = ToggleableState.Off
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Green,
                    checkedColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedColor = Color.Green,
                )
            )
            Text(
                text = "Yes",
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriStateCheckbox(
                state = checked2,
                onClick = {
                    if (checked2 == ToggleableState.Off) {
                        checked2 = ToggleableState.On
                    } else {
                        checked2 = ToggleableState.Off
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Green,
                    checkedColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedColor = Color.Green,
                )
            )
            Text(
                text = "No",
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriStateCheckbox(
                state = checked3,
                onClick = {
                    if (checked3 == ToggleableState.Off) {
                        checked3 = ToggleableState.On
                    } else {
                        checked3 = ToggleableState.Off
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Green,
                    checkedColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedColor = Color.Green,
                )
            )
            Text(
                text = "Other",
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}