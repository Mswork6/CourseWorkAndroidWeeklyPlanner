package com.example.courseworkandroidweeklyplanner.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.data.dayData
import com.example.courseworkandroidweeklyplanner.model.DateTimeFormat
import com.example.courseworkandroidweeklyplanner.model.Day
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun DayCard(
    onClick: () -> Unit,
    day: Day,
    modifier: Modifier = Modifier
) = Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ),
    elevation = CardDefaults.cardElevation(2.dp),
    onClick = onClick,
    modifier = modifier
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(2f)
                .padding(start = 16.dp),
            text = day.name
        )
        Text(
            modifier = Modifier.weight(2f),
            text = day.date.format(DateTimeFormat.formatter)
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "taskOpenClose"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DayCardPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        DayCard(
            onClick = { },
            day = dayData[0],
            modifier = Modifier.fillMaxWidth()
        )

    }
}