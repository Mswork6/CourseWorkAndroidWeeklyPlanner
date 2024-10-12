package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun TaskAddScreen(
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        TaskAddScreenTopBar(
            navigateBackAction = { /*TODO*/ },
            taskAddAction = { /*TODO*/ }
        )
    }
) { padding: PaddingValues ->
    var state by remember {
        mutableStateOf(
            TaskAddInputFieldState(
                taskTitle = "",
                taskDescription = ""
            )
        )
    }
    var isChecked by remember { mutableStateOf(false) }

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
            state = state,
            onTaskTitleValueChange = { state = state.copy(taskTitle = it) },
            onTaskDescriptionValueChange = { state = state.copy(taskDescription = it) },
            modifier = Modifier.fillMaxWidth()
        )
        TaskAddScreenDateInputField(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
        TaskAddScreenDivider()
        TaskAddScreenPriorityInputField(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
        TaskAddScreenDivider()
        TaskAddScreenNotificationInputField(
            isChecked = isChecked,
            onClick = {},
            onTaskNotificationChange = { isChecked = it },
            modifier = Modifier.fillMaxWidth()
        )
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
