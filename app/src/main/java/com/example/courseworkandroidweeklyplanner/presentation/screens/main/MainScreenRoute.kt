package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel

@Composable
fun MainScreenRoute(
    onTaskAddScreen: (taskId: String?, state: String ) -> Unit,
    onTaskEditScreen: (taskId: String, state: String) -> Unit,
    onTaskOpenScreen: (taskId: String, state: String) -> Unit,
) {

//    val viewModelStoreOwnerMainScreen = remember {
//        object : ViewModelStoreOwner {
//            override val viewModelStore: ViewModelStore = ViewModelStore()
//        }
//    }
    val mainViewModel: MainViewModel = hiltViewModel()

    MainScreen(
        viewModel = mainViewModel,
        onTaskAddScreen = onTaskAddScreen,
        onTaskEditScreen = onTaskEditScreen,
        onTaskOpenScreen = onTaskOpenScreen
    )

//    DisposableEffect(key1 = Unit, effect = {
//        onDispose {
//            //Code inside will work as the last thing after leaving the screen
//            viewModelStoreOwnerMainScreen.viewModelStore.clear()
//        }
//    })
}