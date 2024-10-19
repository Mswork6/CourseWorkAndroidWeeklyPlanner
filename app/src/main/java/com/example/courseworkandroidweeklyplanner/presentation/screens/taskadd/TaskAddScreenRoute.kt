package com.example.courseworkandroidweeklyplanner.presentation.screens.taskadd

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.presentation.TaskScreenViewModel

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