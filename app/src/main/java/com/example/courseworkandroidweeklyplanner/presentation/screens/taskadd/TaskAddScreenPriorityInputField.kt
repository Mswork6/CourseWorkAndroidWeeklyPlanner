package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
internal fun TaskAddScreenPriorityInputField(
    editState: Boolean,
    priority: Priority,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier.clickable(
        onClick = onClick,
        enabled = editState
    ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.description_priority),
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = when (priority) {
                Priority.HIGH -> stringResource(Priority.HIGH.description)
                Priority.BASIC -> stringResource(Priority.BASIC.description)
                Priority.LOW -> stringResource(Priority.LOW.description)
            },
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenPriorityInputFieldPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreenPriorityInputField(
            editState = true,
            priority = Priority.HIGH,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}