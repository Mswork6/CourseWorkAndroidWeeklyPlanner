package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.TaskScreenViewModel

@Composable

fun TaskAddScreenRoute(
    taskId: String?,
    screenState: TaskScreenStates?,
    navigateBackAction: () -> Unit,
    taskAddAction: () -> Unit,
) {
//    val viewModelStoreOwnerTaskScreen = object : ViewModelStoreOwner {
//        override val viewModelStore: ViewModelStore = ViewModelStore()
//    }
//    val taskViewModel = viewModel<TaskScreenViewModel>(
//        viewModelStoreOwner = viewModelStoreOwnerTaskScreen,
//        key = "TaskVM"
//    )
    val taskViewModel: TaskScreenViewModel = viewModel()
    val state = taskViewModel.state.collectAsState()

    TaskAddScreen(
        viewModel = taskViewModel,
        state = state.value,
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