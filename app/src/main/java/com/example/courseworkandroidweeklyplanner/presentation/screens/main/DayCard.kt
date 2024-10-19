package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalDate
import java.util.UUID

@Composable
fun DayCard(
    day: Day,
    onDayItemClick: () -> Unit,
    onTaskItemClick: (Task) -> Unit,
    dayItemModifier: Modifier = Modifier,
    taskItemModifier: Modifier = Modifier
) = Column {
    DayItem(
        day = day,
        onClick = onDayItemClick,
        modifier = dayItemModifier
    )
    AnimatedVisibility(day.isExpanded) {
        Column {
            for (task in day.tasks) {
                TaskItem(
                    task = task,
                    onClick = { onTaskItemClick(task) },
                    modifier = taskItemModifier
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DayCardPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        val list: List<Task> = listOf(
            Task(
                id = UUID.randomUUID(),
                name = "Сделать Джаву",
                description = "Сдать до 25 числа",
                deadline = LocalDate.of(2024, 10, 25),
                priority = Priority.HIGH,
                notification = true,
                notificationTime = null,
                isDone = false
            ),

            Task(
                id = UUID.randomUUID(),
                name = "Сделать Джаву",
                description = "Сдать до 25 числа",
                deadline = LocalDate.of(2024, 10, 25),
                priority = Priority.HIGH,
                notification = true,
                notificationTime = null,
                isDone = false
            )
        )
        val day = Day(
            id = 0,
            name = DayEnum.MONDAY.description,
            date = LocalDate.of(2024, 10, 14),
            isExpanded = true,
            tasks = list
        )


        DayCard(
            day = day,
            onDayItemClick = { },
            onTaskItemClick = { },
            dayItemModifier = Modifier,
                //.padding(top = 16.dp),
            taskItemModifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
        )

    }
}