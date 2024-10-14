package com.example.courseworkandroidweeklyplanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import com.example.courseworkandroidweeklyplanner.domain.usecases.ChangeExpandDayCardUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetCurrentWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeekUseCase: GetCurrentWeekUseCase,
    private val getWeekDaysUseCase: GetWeekDaysUseCase,
    private val changeExpandDayCardUseCase: ChangeExpandDayCardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        loadCurrentWeek()
    }

    private fun loadCurrentWeek() {
        val weekDates = getCurrentWeekUseCase.invoke()
        viewModelScope.launch {
            val days = getWeekDaysUseCase.invoke(weekDates)
            _state.update {
                MainScreenState(
                    days = days,
                    weekDates = weekDates
                )
            }
        }
    }

    fun loadPreviousWeek() {
        val currentWeekStart = _state.value.weekDates.weekStartDate
        val previousWeekStart = currentWeekStart.minusWeeks(1)
        val previousWeekEnd = _state.value.weekDates.weekEndDate.minusWeeks(1)
        val weekDates = WeekDates(previousWeekStart, previousWeekEnd)
        val days = getWeekDaysUseCase.invoke(weekDates)
        viewModelScope.launch {
            _state.value = _state.value.copy(
                days = days,
                weekDates = weekDates,
            )
        }
    }

    fun loadNextWeek() {
        val currentWeekStart = _state.value.weekDates.weekStartDate
        val nextWeekStart = currentWeekStart.plusWeeks(1)
        val nextWeekEnd = _state.value.weekDates.weekEndDate.plusWeeks(1)
        val weekDates = WeekDates(nextWeekStart, nextWeekEnd)
        val days = getWeekDaysUseCase.invoke(weekDates)
        viewModelScope.launch {
            _state.value = _state.value.copy(
                days = days,
                weekDates = weekDates,
            )
        }
    }

    fun changeDayCard(day: Day) {
        val newDay = changeExpandDayCardUseCase.invoke(day)

        viewModelScope.launch{
            _state.value = _state.value.copy(
                days = _state.value.days.map { existingDay ->
                    if (existingDay.id == day.id) newDay else existingDay
                }
            )
        }

    }


}

data class MainScreenState(
    val days: List<Day> = emptyList(),
    val weekDates: WeekDates = WeekDates(LocalDate.now(), LocalDate.now())
)

sealed interface State {
    data object Loading : State
    data class Base(
        val days: List<Day>,
        val weekDates: WeekDates
    )
}