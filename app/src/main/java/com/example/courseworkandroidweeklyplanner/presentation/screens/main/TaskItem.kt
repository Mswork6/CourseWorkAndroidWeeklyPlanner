package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalDate
import java.util.UUID

@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = ItemCard(
    shape = MaterialTheme.shapes.large,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ),
    onClick = onClick,
    modifier = modifier

) {
    Icon(
        imageVector = when (task.isDone) {
            true -> ImageVector.vectorResource(R.drawable.icon_checkbox_done)
            false -> ImageVector.vectorResource(R.drawable.baseline_check_box_outline_blank_24)
        },
        contentDescription = stringResource(R.string.description_task_check_box),
        tint = if (task.isDone) {
            Color.Unspecified
        } else {
            when (task.priority) {
                Priority.LOW -> colorResource(R.color.gray)
                Priority.BASIC -> colorResource(R.color.black)
                Priority.HIGH -> colorResource(R.color.red)
                else -> colorResource(R.color.purple_700)
            }
        },

        )
    Spacer(modifier = Modifier.width(16.dp))
    Text(
        text = task.name,
        modifier = Modifier
            .weight(5f)
    )
    if (task.notification) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = stringResource(R.string.description_notification),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskCardWithIconPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskItem(
            onClick = { },
            task = Task(
                UUID.randomUUID(),
                "Отвезти бананы в грузию",
                "Нужно сесть в грузовик и привезти бананы",
                LocalDate.of(2024, 10, 13),
                Priority.HIGH,
                notification = true,
                notificationTime = null,
                isDone = true
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
