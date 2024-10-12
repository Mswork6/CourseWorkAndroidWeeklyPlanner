package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun MainScreenFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = FloatingActionButton(
    onClick = onClick,
    containerColor = MaterialTheme.colorScheme.tertiary,
    contentColor = Color.White,
    shape = CircleShape,
    modifier = modifier
) {
    Icon(
        imageVector = Icons.Default.Add,
        contentDescription = null
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenFloatingActionButtonPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        MainScreenFloatingActionButton(
            onClick = { },
        )
    }
}