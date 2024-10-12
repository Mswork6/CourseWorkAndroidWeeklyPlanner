import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.data.dayData
import com.example.courseworkandroidweeklyplanner.data.weekDates
import com.example.courseworkandroidweeklyplanner.screens.main.DayCard
import com.example.courseworkandroidweeklyplanner.screens.main.MainScreenFloatingActionButton
import com.example.courseworkandroidweeklyplanner.screens.main.MainScreenNavigationBar
import com.example.courseworkandroidweeklyplanner.screens.main.MainScreenSearchFilterButtons
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        MainScreenNavigationBar(
            previousWeekAction = { },
            nextWeekAction = { },
            weekDates = weekDates,
            modifier = Modifier.fillMaxWidth()
                .statusBarsPadding()
        )
    },
    floatingActionButton = {
        MainScreenFloatingActionButton(
            onClick = { }
        )
    }
) { padding: PaddingValues ->
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainScreenSearchFilterButtons(
            searchClickAction = {},
            filterClickAction = {},
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn {
            itemsIndexed(dayData) { _, item ->
                DayCard(
                    onClick = { },
                    day = item,
                    modifier = Modifier
                        .padding(vertical = 8.dp))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    CourseWorkAndroidWeeklyPlannerTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}


