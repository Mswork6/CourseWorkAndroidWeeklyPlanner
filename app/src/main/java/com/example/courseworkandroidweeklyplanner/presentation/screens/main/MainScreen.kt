package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.screens.shared.DatePickerModal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onTaskAddScreen: (taskId: String?, state: String) -> Unit,
    onTaskEditScreen: (taskId: String, state: String) -> Unit,
    onTaskOpenScreen: (taskId: String, state: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            MainScreenNavigationBar(
                previousWeekAction = { viewModel.loadPreviousWeek() },
                nextWeekAction = { viewModel.loadNextWeek() },
                weekDates = state.weekDates,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        floatingActionButton = {
            MainScreenFloatingActionButton(
                onClick = { onTaskAddScreen(null, TaskScreenStates.ADD.name) }
            )
        }
    ) { padding: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainScreenSearchFilterButtons(
                searchClickAction = { viewModel.openCalendar() },
                filterClickAction = { viewModel.showRadioScreen() },
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn {
                itemsIndexed(state.days) { _, item ->
                    DayCard(
                        day = item,
                        onDayItemClick = { viewModel.changeDayCard(item) },
                        onTaskItemClick = { task -> viewModel.openTaskDialogWindow(task) },
                        dayItemModifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        taskItemModifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                top = 16.dp,
                            )
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }

    if (state.isCalendarVisible) {
        DatePickerModal(
            onDateSelected = { dateMillis ->
                viewModel.confirmDate(dateMillis)
                viewModel.searchDate()
            },
            onDismiss = { viewModel.dismissCalendar() }
        )
    }

    state.currentTask?.let { task ->
        TaskDialogWindow(
            task = task,
            onDismissRequest = { viewModel.dismissDialogWindow() },
            onCompleteTask = { viewModel.completeTask(it) },
            onOpenTask = { onTaskOpenScreen(task.id.toString(), TaskScreenStates.OPEN.name) },
            onEditTask = { onTaskEditScreen(task.id.toString(), TaskScreenStates.EDIT.name) },
            onDeleteTask = { viewModel.deleteTask(it) }
        )
    }

    if (state.isRadioScreenVisible) {
        SortDialogWindow(
            selectedOption = state.selectedSort,
            onOptionSelected = { option ->
                viewModel.setSelectedOption(option)
                viewModel.updateData()
            },
            onDismissRequest = { viewModel.hideRadioScreen() },
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}


