package com.example.courseworkandroidweeklyplanner.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.data.taskData
import com.example.courseworkandroidweeklyplanner.model.Task
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun TaskCardWithoutIcon(
    onClick: () -> Unit,
    task: Task,
    modifier: Modifier = Modifier
) = Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ),
    elevation = CardDefaults.cardElevation(2.dp),
    modifier = modifier.clickable(onClick = onClick)
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_checkbox_unchecked),
                contentDescription = "taskCheckBox"
            )
            Text(task.name)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskCardWithoutIconPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskCardWithoutIcon(
            onClick = { },
            task = taskData[0],
            modifier = Modifier.fillMaxWidth()
        )
    }
}