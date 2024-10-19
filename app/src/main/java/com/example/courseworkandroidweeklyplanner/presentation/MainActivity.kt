package com.example.courseworkandroidweeklyplanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreen
import com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd.TaskAddScreen
import com.example.courseworkandroidweeklyplanner.presentation.util.lifecycleIsResumed
import com.example.courseworkandroidweeklyplanner.ui.theme.CourseWorkAndroidWeeklyPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseWorkAndroidWeeklyPlannerTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        MainScreen(onTaskAddScreen = { _: String?,
                                                       state: String ->
                            navController.navigate("task_add_screen/${null}/${state}")
                        }, onTaskEditScreen = { taskId: String,
                                                state: String ->
                            navController.navigate("task_add_screen/${taskId}/${state}")
                        }, onTaskOpenScreen = { taskId: String,
                                                state: String ->
                            navController.navigate("task_add_screen/${taskId}/${state}")
                        })
                    }
                    composable(
                        route = "task_add_screen/{taskId}/{state}",
                        arguments = listOf(navArgument("taskId") {
                            nullable = true
                            type = NavType.StringType
                        }, navArgument("state") {
                            nullable = false
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->

                        val taskId = backStackEntry.arguments?.getString("taskId")
                        val state = backStackEntry.arguments?.getString("state")
                        val stateScreen = state?.let { TaskScreenStates.valueOf(it) }

                        stateScreen?.let {
                            TaskAddScreen(
                                taskId = taskId,
                                screenState = it,
                                navigateBackAction = {
                                    if (backStackEntry.lifecycleIsResumed) navController.popBackStack()
                                },
                                taskAddAction = {
                                    if (backStackEntry.lifecycleIsResumed) navController.popBackStack()
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}