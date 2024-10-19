package com.example.courseworkandroidweeklyplanner.presentation

import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.TaskScreenStates
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

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
