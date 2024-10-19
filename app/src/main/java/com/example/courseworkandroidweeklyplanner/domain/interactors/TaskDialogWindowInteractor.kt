package com.example.courseworkandroidweeklyplanner.domain.interactors

import com.example.courseworkandroidweeklyplanner.domain.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskDialogWindowInteractor {

    private val isTaskDialogWindowVisibleInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isTaskDialogWindowVisible: StateFlow<Boolean> = isTaskDialogWindowVisibleInternal.asStateFlow()

    fun openDialogWindow() = isTaskDialogWindowVisibleInternal.update { true }

    fun dismissDialogWindow() = isTaskDialogWindowVisibleInternal.update { false }

    fun completeTask(task: Task): Task {
        return Task(
            id = task.id,
            name = task.name,
            description = task.description,
            deadline = task.deadline,
            priority = task.priority,
            notification = task.notification,
            notificationTime = task.notificationTime,
            isDone = !task.isDone
        )
    }
}