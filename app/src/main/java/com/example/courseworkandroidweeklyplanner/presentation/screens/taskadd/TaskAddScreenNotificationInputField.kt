package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import android.content.res.Configuration
import android.util.Log
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
import com.example.courseworkandroidweeklyplanner.presentation.util.timeToString
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
internal fun TaskAddScreenNotificationInputField(
    selectedTime: LocalTime?,
    isChecked: Boolean,
    onClick: () -> Unit,
    onTaskNotificationChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
) {
    Column(
        modifier = modifier
            .clickable(
                enabled = isChecked,
                onClick = onClick)
            .weight(3f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.description_notification),
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = if (isChecked && selectedTime != null) {
                timeToString(selectedTime)
            } else {
                stringResource(R.string.description_not_defined)
            },
            style = MaterialTheme.typography.labelSmall
        )
    }
    Log.d("lipec", selectedTime.toString())
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
        onCheckedChange = { checked ->
            onTaskNotificationChange(checked)
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenNotificationInputFieldPreview() {
    var isChecked by remember { mutableStateOf(true) }
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreenNotificationInputField(
            selectedTime = null,
            isChecked = isChecked,
            onClick = {},
            onTaskNotificationChange = { isChecked = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}