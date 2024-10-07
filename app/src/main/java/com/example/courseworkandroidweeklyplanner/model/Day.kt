package com.example.courseworkandroidweeklyplanner.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.time.LocalDate

data class Day(
    val name: String,
    val date: LocalDate,
    val tasks: SnapshotStateList<Task> = mutableStateListOf()
)
