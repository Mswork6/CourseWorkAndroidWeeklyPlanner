package com.example.courseworkandroidweeklyplanner.model

import java.util.*

data class Task(
    val id: UUID,
    var name: String,
    var description: String,
    var deadline: Date,
    var priority: Priority,
    var notification: Boolean,
    var  isDone: Boolean
    )
