package com.example.courseworkandroidweeklyplanner.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.data.weekDates
import com.example.courseworkandroidweeklyplanner.model.DateTimeFormat
import com.example.courseworkandroidweeklyplanner.model.WeekDates
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun MainScreenNavigationBar(
    previousWeekAction: () -> Unit,
    nextWeekAction: () -> Unit,
    weekDates: WeekDates,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    IconButton(onClick = previousWeekAction) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "previousWeek"
        )
    }
    Text(
        "${weekDates.weekStartDate.format(DateTimeFormat.formatter)} - " +
                weekDates.weekEndDate.format(DateTimeFormat.formatter)
    )
    IconButton(onClick = nextWeekAction) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "nextWeek"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenNavigationBarPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        MainScreenNavigationBar(
            previousWeekAction = {},
            nextWeekAction = {},
            weekDates = weekDates,
            modifier = Modifier.fillMaxWidth()

        )
    }
}