package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.TaskScreenViewModel
import com.example.courseworkandroidweeklyplanner.presentation.screens.shared.DatePickerModal
import com.example.courseworkandroidweeklyplanner.presentation.util.convertToLocalDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAddScreen(
    viewModel: TaskScreenViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    var textState by remember {
        mutableStateOf(
            TaskAddInputFieldState(
                taskTitle = "",
                taskDescription = ""
            )
        )
    }
    var isCalendarVisible by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var isTimePickVisible by remember { mutableStateOf(false) }
    var selectedTime: LocalTime? by remember { mutableStateOf(null) }
    var isChecked by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TaskAddScreenTopBar(
                navigateBackAction = { /*TODO*/ },
                taskAddAction = {
                    val notificationTime = if (isChecked) {
                        selectedDate!!.atTime(selectedTime)
                    } else null
                    val newTask = Task(
                        id = UUID.randomUUID(),
                        name = textState.taskTitle,
                        description = textState.taskDescription,
                        deadline = selectedDate!!,
                        priority = Priority.BASIC,
                        notification = isChecked,
                        notificationTime = notificationTime,
                        isDone = false
                    )
                    viewModel.addTask(newTask)
                }
            )
        }
    ) { padding: PaddingValues ->



        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start
        ) {
            TaskAddScreenInputField(
                state = textState,
                onTaskTitleValueChange = { textState = textState.copy(taskTitle = it) },
                onTaskDescriptionValueChange = { textState = textState.copy(taskDescription = it) },
                modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenDateInputField(
                selectedDate = selectedDate,
                onClick = { isCalendarVisible = true },
                modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenDivider()
            TaskAddScreenPriorityInputField(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenDivider()
            TaskAddScreenNotificationInputField(
                selectedTime = selectedTime,
                isChecked = isChecked,
                onClick = { isTimePickVisible = true },
                onTaskNotificationChange = {
                    isChecked = it
                    if (!isChecked) selectedTime = null
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isCalendarVisible) {
            DatePickerModal(
                onDateSelected = { dateMillis ->
                    dateMillis?.let {
                        selectedDate = convertToLocalDate(dateMillis)
                    }
                },
                onDismiss = { isCalendarVisible = false }
            )
        }

        if (isTimePickVisible) {
            DialWithDialogExample(
                onDismiss = { isTimePickVisible = false },
                onConfirm = { timePickerState ->
                    selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    isTimePickVisible = false
                }
            )

        }
    }
}

@Composable
private fun TaskAddScreenDivider(
    modifier: Modifier = Modifier
) = HorizontalDivider(
    modifier = modifier
        .fillMaxWidth()
        .background(color = Color.Gray)
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenPreview() {
//    CourseWorkAndroidWeeklyPlannerTheme {
//        TaskAddScreen(
//            modifier = Modifier.fillMaxSize()
//        )
//    }
}
