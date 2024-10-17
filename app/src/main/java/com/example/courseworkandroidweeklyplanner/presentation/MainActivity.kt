package com.example.courseworkandroidweeklyplanner.presentation

import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd.TaskAddScreen
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val taskScreenViewModel : TaskScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseWorkAndroidWeeklyPlannerTheme {
                TaskAddScreen(taskScreenViewModel)
            }
        }
    }
}