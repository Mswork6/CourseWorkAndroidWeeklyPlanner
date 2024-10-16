package com.example.courseworkandroidweeklyplanner.domain.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Task(
    val id: UUID,
    val name: String,
    val description: String,
    val deadline: LocalDate,
    val priority: Priority,
    val notification: Boolean,
    val notificationTime: LocalDateTime?,
    val  isDone: Boolean
    )
