package com.example.courseworkandroidweeklyplanner.domain.models

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Immutable
data class Task(
    val id: UUID,
    val name: String,
    val description: String?,
    val deadline: LocalDate,
    val priority: Priority,
    val notification: Boolean,
    val notificationTime: LocalDateTime?,
    val isDone: Boolean
)