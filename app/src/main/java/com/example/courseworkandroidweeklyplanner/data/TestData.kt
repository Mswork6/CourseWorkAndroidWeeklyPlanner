package com.example.courseworkandroidweeklyplanner.data

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum.*
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.LocalDate
import java.util.UUID


val dayData: List<Day> = mutableListOf(
    Day(0,
        MONDAY.description,
        LocalDate.of(2024, 10, 8),
        false
    ),
    Day(1,
        TUESDAY.description,
        LocalDate.of(2024, 10, 9),
        false
    ),
    Day(2,
        WEDNESDAY.description,
        LocalDate.of(2024, 10, 10),
        false
    ),
    Day(3,
        THURSDAY.description,
        LocalDate.of(2024, 10, 11),
        false
    ),
    Day(4,
        FRIDAY.description,
        LocalDate.of(2024, 10, 12),
        false
    ),
    Day(5,
        SATURDAY.description,
        LocalDate.of(2024, 10, 13),
        false
    ),
    Day(6,
        SUNDAY.description,
        LocalDate.of(2024, 10, 14),
        false
    )
)

val taskData: List<Task> = mutableListOf(
    Task(
        UUID.randomUUID(),
        "Отвезти бананы в грузию",
        "Нужно сесть в грузовик и привезти бананы",
        LocalDate.of(2024, 10, 13),
        Priority.BASIC,
        notification = true,
        isDone = false
    )
)

val weekDates = WeekDates(LocalDate.of(2024, 10, 7),
    LocalDate.of(2024, 10, 14))