package com.example.courseworkandroidweeklyplanner.domain.models

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class Day(
    val id: Int,
    val name: String,
    val date: LocalDate,
    val isExpanded: Boolean,
    val tasks: List<Task>
)
