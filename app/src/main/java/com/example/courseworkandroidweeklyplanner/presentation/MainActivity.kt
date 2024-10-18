package com.example.courseworkandroidweeklyplanner.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreen
import com.example.courseworkandroidweeklyplanner.presentation.screens.main.MainScreenRoute
import com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd.TaskAddScreen
import com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd.TaskAddScreenRoute
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
                        MainScreenRoute(
                            onTaskAddScreen = { taskId: String?, state: String? ->
                                Log.d("chelyabinsk", "taskId: $taskId")
                                Log.d("chelyabinsk", "state: $state")
                                navController.navigate("task_add_screen/${taskId}/${state}")
                            },
                            onTaskEditScreen = { taskId: String?, state: String? ->
                                navController.navigate("task_add_screen/${taskId}/${state}") },
                            onTaskOpenScreen = { taskId: String?, state: String? ->
                                navController.navigate("task_add_screen/${taskId}/${state}") }
                        )

                    }
                    composable(route = "task_add_screen/{taskId}/{state}",
                        arguments = listOf(
                            navArgument("taskId") { type = NavType.StringType },
                            navArgument("state") { type = NavType.StringType }
                        )) { backStackEntry ->

                        val taskId = backStackEntry.arguments?.getString("taskId")
                        val state = backStackEntry.arguments?.getString("state")
                        val stateScreen = state?.let { TaskScreenStates.valueOf(it) }

                        TaskAddScreenRoute(
                            taskId = taskId,
                            screenState = stateScreen,
                            navigateBackAction = { navController.popBackStack() },
                            taskAddAction = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}