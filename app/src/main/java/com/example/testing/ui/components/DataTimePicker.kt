package com.example.testing.ui.components

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
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.now

@Composable
fun DataTimePicker(modifier: Modifier = Modifier) {
    var showDataPicker by remember { mutableStateOf(false) }

    WheelDatePickerView(
        modifier = modifier,
        showDatePicker = showDataPicker,
        height = 200.dp,
        title = "Select Date",
        doneLabel = "Done",
        dateTimePickerView = DateTimePickerView.BOTTOM_SHEET_VIEW,
        yearsRange = LocalDate.now().year - 100..LocalDate.now().year,
        onDoneClick = {
            showDataPicker = false
        },
        onDismiss = {
            showDataPicker = false
        },
    )
    Button(onClick = { showDataPicker = true }) {
        Text("Date Picker")
    }
}
