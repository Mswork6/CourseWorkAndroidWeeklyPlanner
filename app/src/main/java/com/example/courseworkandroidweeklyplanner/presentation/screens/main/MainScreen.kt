package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.data.taskData
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.screens.shared.DatePickerModal
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme


@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
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
                onClick = { }
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
                filterClickAction = {},
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
            onOpenTask = { },
            onEditTask = { },
            onDeleteTask = { viewModel.deleteTask(it) }
        )
    }


}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}


