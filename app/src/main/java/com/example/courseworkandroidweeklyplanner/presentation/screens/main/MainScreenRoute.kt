package com.example.courseworkandroidweeklyplanner.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.presentation.MainViewModel
import com.example.courseworkandroidweeklyplanner.presentation.util.viewModelStoreOwnerMainScreen

@Composable
fun MainScreenRoute(
    onTaskAddScreen: (taskId: String?, state: String? ) -> Unit,
    onTaskEditScreen: (taskId: String?, state: String?) -> Unit,
    onTaskOpenScreen: (taskId: String?, state: String?) -> Unit,
) {

//    val mainViewModel = hiltViewModel<MainViewModel>(
//    viewModelStoreOwner = viewModelStoreOwnerMainScreen,
//        key = "MainVM"
//    )
    val mainViewModel: MainViewModel = hiltViewModel()
    val state by mainViewModel.state.collectAsState()

    MainScreen(
        viewModel = mainViewModel,
        //state = state,
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