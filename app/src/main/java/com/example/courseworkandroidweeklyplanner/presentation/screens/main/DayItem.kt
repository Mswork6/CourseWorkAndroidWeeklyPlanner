package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum.MONDAY
import com.example.courseworkandroidweeklyplanner.presentation.util.dateToString
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalDate

@Composable
fun DayItem(
    day: Day,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationState by animateFloatAsState(
        targetValue = if(day.isExpanded) 180f else 0f,
        label = stringResource(R.string.descipition_dayitem_animation)
    )

    ItemCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier
                .weight(2f),
            text = day.name,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier.weight(2f),
            text = dateToString(day.date),
        )
        Icon(
            modifier = Modifier
                .weight(1f)
                .rotate(rotationState),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(R.string.description_show_hide_tasks)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DayCardPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        val day = Day(
            id = 0,
            name = MONDAY.description,
            date = LocalDate.of(2024, 10, 8),
            isExpanded = true,
            tasks = listOf()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DayItem(
                onClick = { },
                day = day,
                modifier = Modifier.fillMaxWidth()
            )
            DayItem(
                onClick = { },
                day = day,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
