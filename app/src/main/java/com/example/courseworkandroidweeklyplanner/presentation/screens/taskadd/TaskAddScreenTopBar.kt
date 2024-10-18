package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

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
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskAddScreenTopBar(
    state: TaskScreenStates?,
    navigateBackAction: () -> Unit,
    taskAddAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = navigateBackAction)
            {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.action_back)
                )
            }
        },
        actions = {
            TextButton(
                onClick = taskAddAction,
                enabled = if (state == TaskScreenStates.ADD ||
                    state == TaskScreenStates.EDIT
                ) true
                else false
            ) {
                Text(
                    text = when (state) {
                        TaskScreenStates.ADD ->
                            stringResource(id = R.string.action_add_task)

                        TaskScreenStates.EDIT ->
                            stringResource(id = R.string.action_edit_task)

                        TaskScreenStates.OPEN -> ""
                        null -> "NULL????"
                    },
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskAddScreenTopBarPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        TaskAddScreenTopBar(
            state = TaskScreenStates.OPEN,
            navigateBackAction = {},
            taskAddAction = {},
        )
    }
}