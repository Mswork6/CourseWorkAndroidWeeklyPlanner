package com.example.courseworkandroidweeklyplanner.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courseworkandroidweeklyplanner.R
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme

@Composable
fun MainScreenSearchFilterButtons(
    searchClickAction: () -> Unit,
    filterClickAction: () -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.End
) {

    IconButton(
        onClick = searchClickAction
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "searchButton"
        )
    }

    IconButton(onClick = filterClickAction) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_filter_alt_24),
            contentDescription = "filterButton"
        )
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenSearchFilterButtonsPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        MainScreenSearchFilterButtons(
            searchClickAction = {},
            filterClickAction = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}