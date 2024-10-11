package com.example.courseworkandroidweeklyplanner.screens.taskadd

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTaskScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        ActionButtons()
        TaskNameField()
        TaskInfoField()
        DateField()
        Divider()
        PriorityField()
        Divider()
        NotificationField()
    }
}

@Preview
@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Back"
            )
        }

        TextButton(onClick = {}) {
            Text(
                text = "Добавить задачу",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun TaskNameField() {
    val taskNameState = remember { mutableStateOf("") }

    BasicTextField(
        value = taskNameState.value,
        onValueChange = { taskNameState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(8.dp)) {
                if (taskNameState.value.isEmpty()) {
                    Text("Название задачи", color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun TaskInfoField() {
    val taskDescriptionState = remember { mutableStateOf("") }

    BasicTextField(
        value = taskDescriptionState.value,
        onValueChange = { taskDescriptionState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 10.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(8.dp)) {
                if (taskDescriptionState.value.isEmpty()) {
                    Text(
                        "Описание задачи",
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        }
    )
}


@Preview
@Composable
fun DateField() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Дата")
            Text(
                text = "Не указана",
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }

        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null
        )
    }
}


@Composable
fun PriorityField() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        Text(text = "Приоритет", fontSize = 16.sp)
        Text(text = "Стандартный", fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun NotificationField() {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Напоминание")
            Text(
                text = "Не установлено", fontSize = 12.sp, color = Color.Gray
            )
        }

        Switch(
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.White,
                uncheckedTrackColor = Color.White,

                checkedBorderColor = Color.Black,
                uncheckedBorderColor = Color.Black,


                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                uncheckedThumbColor = Color.Black,
            ),
            checked = isChecked.value, onCheckedChange = { isChecked.value = it })
    }
}


@Composable
fun Divider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
    )
}