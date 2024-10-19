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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.TaskScreenViewModel
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.PriorityDialogWindow
import com.example.courseworkandroidweeklyplanner.presentation.screens.shared.DatePickerModal
import com.example.courseworkandroidweeklyplanner.presentation.util.PastOrPresentSelectableDates
import com.example.courseworkandroidweeklyplanner.presentation.util.convertToLocalDate
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAddScreen(
    viewModel: TaskScreenViewModel = viewModel(),
    taskId: String?,
    screenState: TaskScreenStates?,
    navigateBackAction: () -> Unit,
    taskAddAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    viewModel.setTaskScreenState(screenState)
    viewModel.checkScreenState(taskId = taskId)

    Scaffold(modifier = modifier, topBar = {
        TaskAddScreenTopBar(
            state = state.screenState,
            navigateBackAction = navigateBackAction,
            taskAddAction = {
                when (state.screenState) {
                    TaskScreenStates.ADD -> {
                        if (viewModel.addTask())
                            taskAddAction()
                    }

                    TaskScreenStates.EDIT -> {
                        if (viewModel.editTask())
                            taskAddAction()
                    }

                    TaskScreenStates.OPEN -> {}
                    null -> {}
                }
            }

        )
    }) { padding: PaddingValues ->


        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start
        ) {
            TaskAddScreenInputField(
                nameText = state.taskName,
                taskNameError = state.taskNameError,
                descriptionText = state.taskDescription,
                editState = state.editState,
                onTaskTitleValueChange = { viewModel.setTaskName(it) },
                onTaskDescriptionValueChange = { viewModel.setTaskDescription(it) },
                modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenPriorityInputField(
                editState = state.editState,
                priority = state.taskPriority,
                onClick = { viewModel.showPriorityScreen() }, modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenDivider()
            TaskAddScreenDateInputField(
                selectedDate = state.taskDeadLine,
                editState = state.editState,
                onClick = { viewModel.openTaskCalendar() },
                modifier = Modifier.fillMaxWidth()
            )
            TaskAddScreenDivider()
            TaskAddScreenNotificationInputField(
                selectedTime = state.taskNotificationTime,
                isChecked = state.taskNotification,
                editState = state.editState,
                onClick = { viewModel.openTaskNotificationWindow() },
                onTaskNotificationChange = {
                    viewModel.setTaskNotification(it)
                    if (!it) viewModel.setTaskNotificationTime(null)
                    else viewModel.setTaskNotificationTime(LocalTime.now().plusMinutes(10))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (state.isTaskCalendarVisible) {
            DatePickerModal(
                selectableDates = PastOrPresentSelectableDates,
                onDateSelected = { dateMillis ->
                    dateMillis?.let {
                        val deadLine = convertToLocalDate(dateMillis)
                        viewModel.setTaskDeadLine(deadLine)
                    }
                }, onDismiss = { viewModel.closeTaskCalendar() })
        }

        if (state.isTaskNotificationWindowVisible) {
            DialWithDialogExample(onDismiss = { viewModel.closeTaskNotificationWindow() },
                onConfirm = { timePickerState ->
                    val notificationTime =
                        LocalTime.of(timePickerState.hour, timePickerState.minute)
                    viewModel.closeTaskNotificationWindow()
                    viewModel.setTaskNotificationTime(notificationTime)
                })

        }

        if (state.isPriorityScreenVisible) {
            PriorityDialogWindow(
                selectedOption = state.taskPriority,
                onOptionSelected = { option ->
                    viewModel.setSelectedOption(option)
                },
                onDismissRequest = { viewModel.hidePriorityScreen() },
                modifier = Modifier.fillMaxWidth(0.9f)
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
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreen(
        taskId = null,
        screenState =  TaskScreenStates.ADD,
        navigateBackAction = {},
        taskAddAction = { },
        )
    }
}
