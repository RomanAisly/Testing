package com.example.testing.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.TimeFormat
import network.chaintech.kmp_date_time_picker.utils.now

@Composable
fun CustomDatePicker(modifier: Modifier = Modifier) {
    var showDatePicker by remember { mutableStateOf(false) }

    WheelDatePickerView(
        modifier = modifier.padding(vertical = 4.dp),
        showDatePicker = showDatePicker,
        height = 200.dp,
        title = "Select Date",
        doneLabel = "Done",
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        yearsRange = LocalDate.now().year - 100..LocalDate.now().year,
        onDoneClick = {
            showDatePicker = false
        }
    )
    Button(onClick = { showDatePicker = true }) {
        Text("Date Picker")
    }
}

@Composable
fun CustomTimePicker(modifier: Modifier = Modifier) {
    var showTimePicker by remember { mutableStateOf(false) }

    WheelTimePickerView(
        modifier = modifier.padding(vertical = 4.dp),
        showTimePicker = showTimePicker,
        height = 200.dp,
        title = "Select Time",
        doneLabel = "Done",
        timeFormat = TimeFormat.HOUR_24,
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        onDoneClick = {
            showTimePicker = false
        }
    )
    Button(onClick = { showTimePicker = true }) {
        Text("Time Picker")
    }
}