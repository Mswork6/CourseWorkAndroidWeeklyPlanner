package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.TaskScreenViewModel
import com.example.courseworkandroidweeklyplanner.presentation.util.viewModelStoreOwnerTaskScreen

@Composable

fun TaskAddScreenRoute(
    taskId: String?,
    screenState: TaskScreenStates?,
    navigateBackAction: () -> Unit,
    taskAddAction: () -> Unit,
) {
//    val taskViewModel = hiltViewModel<TaskScreenViewModel>(
//        viewModelStoreOwner = viewModelStoreOwnerTaskScreen,
//        key = "TaskVM"
//    )
    val taskViewModel: TaskScreenViewModel = hiltViewModel()
    //val state by taskViewModel.state.collectAsState()

    TaskAddScreen(
        viewModel = taskViewModel,
        //state = state,
        taskId = taskId,
        screenState = screenState,
        navigateBackAction = navigateBackAction,
        taskAddAction = taskAddAction
    )

//    DisposableEffect(key1 = Unit, effect = {
//        onDispose {
//            //Code inside will work as the last thing after leaving the screen
//            viewModelStoreOwnerTaskScreen.viewModelStore.clear()
//        }
//    })
}