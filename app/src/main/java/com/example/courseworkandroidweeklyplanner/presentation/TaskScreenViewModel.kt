package com.example.courseworkandroidweeklyplanner.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.courseworkandroidweeklyplanner.data.repository.TaskRepositoryInteractor
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.usecases.UpdateWeekDaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val updateWeekDaysUseCase: UpdateWeekDaysUseCase,
    private val taskRepositoryInteractor: TaskRepositoryInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> = _state.asStateFlow()

    fun addTask() {
        viewModelScope.launch {

            val currentName = _state.value.taskName.trim()
            when {
                currentName.isEmpty() -> {
                    _state.update {
                        it.copy(taskNameError = "Заголовок не может быть пустым")
                    }
                }
                currentName.length > 100 -> {
                    _state.update {
                        it.copy(taskNameError = "Заголовок не может превышать 100 символов")
                    }
                }
                else -> {
                    _state.update {
                        it.copy(taskNameError = null)
                    }

                    val newTask = Task(
                        id = UUID.randomUUID(),
                        name = _state.value.taskName,
                        description = _state.value.taskDescription,
                        priority = _state.value.taskPriority,
                        deadline = _state.value.taskDeadLine,
                        notification = _state.value.taskNotification,
                        notificationTime = _state.value.taskNotificationTime
                            ?.let { _state.value.taskDeadLine.atTime(it) },
                        isDone = false
                    )
                    Log.d("lipec", "created object")
                    taskRepositoryInteractor.addTask(task = newTask)
                }
            }
        }
    }

    fun openTaskCalendar() {
        viewModelScope.launch {
            _state.update { it.copy(isTaskCalendarVisible = true) }
        }
    }

    fun closeTaskCalendar() {
        viewModelScope.launch {
            _state.update { it.copy(isTaskCalendarVisible = false) }
        }
    }

    fun openTaskNotificationWindow() {
        viewModelScope.launch {
            _state.update { it.copy(isTaskNotificationWindowVisible = true) }
        }
    }

    fun closeTaskNotificationWindow() {
        viewModelScope.launch {
            _state.update { it.copy(isTaskNotificationWindowVisible = false) }
        }
    }

    fun setTaskName(text: String){
        viewModelScope.launch {
            _state.update { it.copy(taskName = text) }
        }
    }

    fun setTaskDescription(text: String){
        viewModelScope.launch {
            _state.update { it.copy(taskDescription = text) }
        }
    }

    fun setTaskDeadLine(deadLine: LocalDate){
        viewModelScope.launch {
            _state.update { it.copy(taskDeadLine = deadLine) }
        }
    }

    fun setTaskPriority(priority: Priority){
        viewModelScope.launch {
            _state.update { it.copy(taskPriority = priority) }
        }
    }

    fun setTaskNotification(taskNotification: Boolean){
        viewModelScope.launch {
            _state.update { it.copy(taskNotification = taskNotification) }
        }
    }

    fun setTaskNotificationTime(taskNotificationTime: LocalTime?){
        viewModelScope.launch {
            _state.update { it.copy(taskNotificationTime = taskNotificationTime) }
        }
    }

}


data class TaskScreenState(
    val taskName: String = "",
    val taskDescription: String? = null,
    val isTaskCalendarVisible: Boolean = false,
    val taskDeadLine: LocalDate = LocalDate.now(),
    val isPriorityWindowVisible: Boolean = false,
    val taskPriority: Priority = Priority.BASIC,
    val isTaskNotificationWindowVisible: Boolean = false,
    val taskNotification: Boolean = false,
    val taskNotificationTime: LocalTime? = null,
    val taskNameError: String? = null,
)