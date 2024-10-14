package com.example.courseworkandroidweeklyplanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetCurrentWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    private val getCurrentWeekUseCase = GetCurrentWeekUseCase()
    private val getWeekDaysUseCase = GetWeekDaysUseCase()

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getCurrentWeekUseCase = getCurrentWeekUseCase,
            getWeekDaysUseCase = getWeekDaysUseCase
        ) as T
    }
}