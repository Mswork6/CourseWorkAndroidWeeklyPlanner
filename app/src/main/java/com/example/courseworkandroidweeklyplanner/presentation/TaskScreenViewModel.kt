package com.example.courseworkandroidweeklyplanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseworkandroidweeklyplanner.data.repository.TaskRepositoryInteractor
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val taskRepositoryInteractor: TaskRepositoryInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> = _state.asStateFlow()

    private fun validateTask(): Boolean {
        val currentName = _state.value.taskName.trim()
        when {
            currentName.isEmpty() -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(taskNameError = "Заголовок не может быть пустым")
                    }
                }
                return false
            }

            currentName.length > 100 -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(taskNameError = "Заголовок не может превышать 100 символов")
                    }
                }
                return false
            }
        }
        return true
    }

    fun addTask(): Boolean {

        if (validateTask()) {
            viewModelScope.launch {
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
                taskRepositoryInteractor.addTask(task = newTask)
            }
            return true
        } else return false
    }

    fun editTask(): Boolean {
        if (validateTask()) {
            viewModelScope.launch {
                val oldTask = _state.value.taskId?.let { taskRepositoryInteractor.getTask(it) }
                val editedTask = oldTask?.copy(
                    name = _state.value.taskName,
                    description = _state.value.taskDescription,
                    deadline = _state.value.taskDeadLine,
                    priority = _state.value.taskPriority,
                    notification = _state.value.taskNotification,
                    notificationTime = _state.value.taskNotificationTime
                        ?.let { _state.value.taskDeadLine.atTime(it) },
                )
                if (editedTask != null) {
                    taskRepositoryInteractor.updateTask(editedTask)
                }
            }
            return true
        }
        return false
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

    fun setTaskName(text: String) {
        viewModelScope.launch {
            _state.update { it.copy(taskName = text) }
        }
    }

    fun setTaskDescription(text: String) {
        viewModelScope.launch {
            _state.update { it.copy(taskDescription = text) }
        }
    }

    fun setTaskDeadLine(deadLine: LocalDate) {
        viewModelScope.launch {
            _state.update { it.copy(taskDeadLine = deadLine) }
        }
    }

    fun setTaskNotification(taskNotification: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(taskNotification = taskNotification) }
        }
    }

    fun setTaskNotificationTime(taskNotificationTime: LocalTime?) {
        viewModelScope.launch {
            _state.update { it.copy(taskNotificationTime = taskNotificationTime) }
        }
    }

    fun setTaskScreenState(state: TaskScreenStates?) {
        viewModelScope.launch {
            _state.update { it.copy(screenState = state) }
        }

    }

    fun checkScreenState(taskId: String?) {
        viewModelScope.launch {
            when (_state.value.screenState) {
                TaskScreenStates.OPEN -> taskId?.let { updateData(taskId = it, editState = false) }
                TaskScreenStates.EDIT -> taskId?.let { updateData(taskId = it, editState = true) }
                TaskScreenStates.ADD -> clearData()
                null -> clearData()
            }
        }


    }

    private fun clearData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    taskId = null,
                    taskName = "",
                    taskDescription = null,
                    isTaskCalendarVisible = false,
                    taskDeadLine = LocalDate.now(),
                    isPriorityWindowVisible = false,
                    taskPriority = Priority.BASIC,
                    isTaskNotificationWindowVisible = false,
                    taskNotification = false,
                    taskNotificationTime = null,
                    taskNameError = null,
                    editState = true
                )
            }
        }
    }

    private fun updateData(taskId: String, editState: Boolean) {
        viewModelScope.launch {
            val id = UUID.fromString(taskId)
            val task = taskRepositoryInteractor.getTask(id)

            _state.update {
                it.copy(
                    taskId = id,
                    taskName = task.name,
                    taskDescription = task.description,
                    taskDeadLine = task.deadline,
                    taskPriority = task.priority,
                    taskNotification = task.notification,
                    taskNotificationTime = task.notificationTime?.toLocalTime(),
                    editState = editState
                )
            }
        }

    }

    fun showPriorityScreen() {
        _state.update {
            it.copy(isPriorityScreenVisible = true)
        }
    }


    fun hidePriorityScreen() {
        _state.update {
            it.copy(isPriorityScreenVisible = false)
        }
    }

    fun setSelectedOption(priority: Priority) {
        _state.update {
            it.copy(taskPriority = priority)
        }
    }

}


data class TaskScreenState(
    val taskId: UUID? = null,
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
    val screenState: TaskScreenStates? = null,
    val editState: Boolean = true,
    val isPriorityScreenVisible: Boolean = false
)
