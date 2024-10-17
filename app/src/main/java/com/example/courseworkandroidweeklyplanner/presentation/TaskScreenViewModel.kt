package com.example.courseworkandroidweeklyplanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseworkandroidweeklyplanner.data.repository.TaskRepositoryInteractor
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.usecases.UpdateWeekDaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val updateWeekDaysUseCase: UpdateWeekDaysUseCase,
    private val taskRepositoryInteractor: TaskRepositoryInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> = _state.asStateFlow()

    fun addTask(task: Task) {
        viewModelScope.launch {
            val newTask = Task(
                id = UUID.randomUUID(),
                name = _state.value.taskName,
                description = _state.value.taskDescription,
                priority = _state.value.taskPriority,
                deadline = _state.value.taskDeadLine,
                notification = _state.value.taskNotification,
                notificationTime = _state.value.taskNotificationTime,
                isDone = false
            )
                taskRepositoryInteractor.addTask(task = newTask)
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
    val taskNotification: Boolean = false,
    val taskNotificationTime: LocalDateTime? = null,
)