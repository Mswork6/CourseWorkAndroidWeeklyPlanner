package com.example.courseworkandroidweeklyplanner.screens.taskadd

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskAddScreenTopBar(
    navigateBackAction: () -> Unit,
    taskAddAction: () -> Unit,
    modifier: Modifier = Modifier,
) = TopAppBar(
    modifier = modifier,
    title = {},
    navigationIcon = {
        IconButton(onClick = navigateBackAction) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.action_back)
            )
        }
    },
    actions = {
        TextButton(onClick = taskAddAction) {
            Text(
                text = stringResource(id = R.string.action_add_task),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenTopBarPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreenTopBar(
            navigateBackAction = {},
            taskAddAction = {},
        )
    }
}