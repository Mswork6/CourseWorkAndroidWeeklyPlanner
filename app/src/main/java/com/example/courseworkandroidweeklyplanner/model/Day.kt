package com.example.courseworkandroidweeklyplanner.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.time.LocalDate

@Immutable
data class Day(
    val name: String,
    val date: LocalDate,
    val tasks: List<Task> = listOf()
)
