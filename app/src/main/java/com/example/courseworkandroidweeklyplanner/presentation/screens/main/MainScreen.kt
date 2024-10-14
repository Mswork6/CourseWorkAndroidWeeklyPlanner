import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.DayCard
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreenFloatingActionButton
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreenNavigationBar
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreenSearchFilterButtons


@Composable
fun MainScreen(
    viewModel : MainViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            MainScreenNavigationBar(
                previousWeekAction = { viewModel.loadPreviousWeek() },
                nextWeekAction = { viewModel.loadNextWeek() },
                weekDates = state.weekDates,
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
                itemsIndexed(state.days) { _, item ->
                    DayCard(
                        onClick = { },
                        day = item,
                        modifier = Modifier
                            .padding(vertical = 8.dp))
                }
            }
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun MainScreenPreview() {
//    CourseWorkAndroidWeeklyPlannerTheme {
//        MainScreen(
//            viewModel = ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java],
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}


