package com.example.courseworkandroidweeklyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseworkandroidweeklyplanner.data.dayData
import com.example.courseworkandroidweeklyplanner.model.DateTimeFormat
import com.example.courseworkandroidweeklyplanner.model.Day
import com.example.courseworkandroidweeklyplanner.screens.taskadd.AddTaskScreen
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseWorkAndroidWeeklyPlannerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = { AddButton() },
                    floatingActionButtonPosition = FabPosition.End
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        AddTaskScreen()
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    NavButtons(LocalDate.now(), LocalDate.now())
    Buttons()
    LazyColumn {
        itemsIndexed(dayData) { _, item ->
            DayItem(item)
            TaskCard()
        }

    }
}


@Composable
fun DayItem(day: Day) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 7.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 15.dp),
                text = day.name
            )
            Text(
                modifier = Modifier.weight(2f),
                text = day.date.format(DateTimeFormat.formatter)
            )
            IconButton(modifier = Modifier.weight(1f),
                onClick = {

                }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "taskOpenClose"
                )
            }

        }

    }
}

@Preview
@Composable
fun Buttons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        IconButton(
            onClick = {}) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "searchButton"
            )
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_filter_alt_24),
                contentDescription = "filterButton"
            )
        }

    }

}

@Composable
fun AddButton() {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Composable
fun NavButtons(startOfWeek: LocalDate, endOfWeek: LocalDate) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "previousWeek"
            )
        }
        Text(
            "${startOfWeek.format(DateTimeFormat.formatter)} - " +
                    endOfWeek.format(DateTimeFormat.formatter)
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "nextWeek"
            )
        }
    }
}

@Preview
@Composable
fun TaskCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(
                start = 20.dp, end = 10.dp,
                top = 5.dp, bottom = 5.dp
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_checkbox_unchecked),
                    contentDescription = "taskCheckBox"
                )
                Text("Отвезти бананы в грузию")
            }
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Default.Notifications,
                contentDescription = null
            )
        }
    }
}
