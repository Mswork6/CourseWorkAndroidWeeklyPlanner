package com.example.courseworkandroidweeklyplanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseworkandroidweeklyplanner.data.repository.TaskRepositoryInteractor
import com.example.courseworkandroidweeklyplanner.domain.interactors.CalendarInteractor
import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates

import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.UpdateWeekDaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeekUseCase: GetWeekUseCase,
    private val getWeekDaysUseCase: GetWeekDaysUseCase,
    private val updateWeekDaysUseCase: UpdateWeekDaysUseCase,
    private val calendarInteractor: CalendarInteractor,
    private val taskRepositoryInteractor: TaskRepositoryInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                taskRepositoryInteractor.init()
                val weekDates = getWeekUseCase(LocalDate.now())
                _state.update {
                    MainScreenState(
                        days = getWeekDaysUseCase(weekDates, taskRepositoryInteractor.getData()),
                        weekDates = weekDates
                    )
                }
            }
            launch {
                calendarInteractor.isCalendarVisible.collect { isCalendarVisible ->
                    _state.update {
                        it.copy(isCalendarVisible = isCalendarVisible)
                    }
                }
            }
            launch {
                calendarInteractor.selectedDate.collect { searchDate ->
                    _state.update {
                        it.copy(searchDate = searchDate)
                    }
                }
            }
        }
    }

    fun loadPreviousWeek() {
        val currentWeekStart = _state.value.weekDates.weekStartDate
        val previousWeekStart = currentWeekStart.minusWeeks(1)
        val previousWeekEnd = _state.value.weekDates.weekEndDate.minusWeeks(1)
        val weekDates = WeekDates(previousWeekStart, previousWeekEnd)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    days = getWeekDaysUseCase(weekDates, taskRepositoryInteractor.getData()),
                    weekDates = weekDates,
                )
            }
        }
    }

    fun loadNextWeek() {
        val currentWeekStart = _state.value.weekDates.weekStartDate
        val nextWeekStart = currentWeekStart.plusWeeks(1)
        val nextWeekEnd = _state.value.weekDates.weekEndDate.plusWeeks(1)
        val weekDates = WeekDates(nextWeekStart, nextWeekEnd)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    days = getWeekDaysUseCase(weekDates, taskRepositoryInteractor.getData()),
                    weekDates = weekDates
                )
            }
        }
    }

    fun changeDayCard(day: Day) {
        val changedDay = day.copy(isExpanded = day.isExpanded.not())

        viewModelScope.launch {
            _state.update {
                it.copy(
                    days = it.days.map { existingDay ->
                        if (existingDay.id == day.id) changedDay else existingDay
                    }
                )
            }
        }
    }

    fun openCalendar() = calendarInteractor.openCalendar()

    fun dismissCalendar() = calendarInteractor.dismissCalendar()

    fun confirmDate(dateMillis: Long?) {
        viewModelScope.launch {
            calendarInteractor.confirmDate(dateMillis)
            _state.update {
                it.copy(searchDate = calendarInteractor.selectedDate.value)
            }
        }
    }

    fun searchDate() {
        viewModelScope.launch {
            _state.value.searchDate?.let { searchDate ->
                val weekDates = getWeekUseCase(searchDate)

                _state.update {
                    it.copy(
                        weekDates = weekDates,
                        days = getWeekDaysUseCase(weekDates, taskRepositoryInteractor.getData())
                    )
                }
            }
        }
    }

    fun openTaskDialogWindow(task: Task) {
        viewModelScope.launch {
            _state.update {
                it.copy(currentTask = task)
            }
        }
    }


    fun dismissDialogWindow() {
        viewModelScope.launch {
            _state.update {
                it.copy(currentTask = null)
            }
        }
    }

    fun updateData() {
        viewModelScope.launch {
            val days = updateWeekDaysUseCase(
                daysList = _state.value.days,
                taskList = taskRepositoryInteractor.getData()
            )
            _state.update {
                it.copy(
                    days = days,
                )
            }
        }
    }


    fun completeTask(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isDone = task.isDone.not())
            taskRepositoryInteractor.updateTask(updatedTask)
            val days = updateWeekDaysUseCase(
                daysList = _state.value.days,
                taskList = taskRepositoryInteractor.getData()
            )
            _state.update {
                it.copy(
                    days = days,
                    currentTask = null
                )
            }
        }
    }

    fun deleteTask(id: UUID) {
        viewModelScope.launch {
            taskRepositoryInteractor.deleteTask(id)
            val days = updateWeekDaysUseCase(
                daysList = _state.value.days,
                taskList = taskRepositoryInteractor.getData()
            )
            _state.update {
                it.copy(
                    days = days,
                    currentTask = null
                )
            }
        }
    }

    fun setTaskEditState(state: TaskScreenStates) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    taskEditState = state
                )
            }
        }
    }


}

data class MainScreenState(
    val days: List<Day> = emptyList(),
    val weekDates: WeekDates = WeekDates(LocalDate.now(), LocalDate.now()),
    val isCalendarVisible: Boolean = false,
    val searchDate: LocalDate? = null,
    val currentTask: Task? = null,
    val taskEditState: TaskScreenStates? = null

)

sealed interface State {
    data object Loading : State
    data class Base(
        val days: List<Day>,
        val weekDates: WeekDates
    )
}