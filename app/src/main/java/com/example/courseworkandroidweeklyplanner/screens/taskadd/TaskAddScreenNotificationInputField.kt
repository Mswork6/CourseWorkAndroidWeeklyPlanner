package com.example.courseworkandroidweeklyplanner.screens.taskadd

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
internal fun TaskAddScreenNotificationInputField(
    isChecked: Boolean,
    onClick: () -> Unit,
    onTaskNotificationChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.notification_field_name),
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = stringResource(R.string.notification_field_value),
            style = MaterialTheme.typography.labelSmall
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
        checked = isChecked,
        onCheckedChange = onTaskNotificationChange
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenNotificationInputFieldPreview() {
    var isChecked by remember { mutableStateOf(false) }
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreenNotificationInputField(
            isChecked = isChecked,
            onClick = {},
            onTaskNotificationChange = { isChecked = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}