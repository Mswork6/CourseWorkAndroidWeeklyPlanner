package com.example.courseworkandroidweeklyplanner.model

import java.time.LocalDate
import java.util.*

data class Task(
    val id: UUID,
    var name: String,
    var description: String,
    var deadline: LocalDate,
    var priority: Priority,
    var notification: Boolean,
    var  isDone: Boolean
    )
