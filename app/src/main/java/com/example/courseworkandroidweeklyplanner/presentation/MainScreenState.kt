package com.example.courseworkandroidweeklyplanner.presentation

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.SortStates
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.LocalDate

data class MainScreenState(
    val tasks: List<Task> = emptyList(),
    val days: List<Day> = emptyList(),
    val weekDates: WeekDates = WeekDates(LocalDate.now(), LocalDate.now()),
    val isCalendarVisible: Boolean = false,
    val searchDate: LocalDate? = null,
    val currentTask: Task? = null,
    val isRadioScreenVisible: Boolean = false,
    val selectedSort: SortStates = SortStates.STANDARD

)
