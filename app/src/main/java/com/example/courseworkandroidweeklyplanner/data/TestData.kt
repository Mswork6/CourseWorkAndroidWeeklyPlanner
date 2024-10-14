package com.example.courseworkandroidweeklyplanner.data

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum.*
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.LocalDate
import java.util.UUID


val dayData: List<Day> = mutableListOf(
    Day(
        MONDAY.description,
        LocalDate.of(2024, 10, 8)
    ),
    Day(
        TUESDAY.description,
        LocalDate.of(2024, 10, 9)
    ),
    Day(
        WEDNESDAY.description,
        LocalDate.of(2024, 10, 10)
    ),
    Day(
        THURSDAY.description,
        LocalDate.of(2024, 10, 11)
    ),
    Day(
        FRIDAY.description,
        LocalDate.of(2024, 10, 12)
    ),
    Day(
        SATURDAY.description,
        LocalDate.of(2024, 10, 13)
    ),
    Day(
        SUNDAY.description,
        LocalDate.of(2024, 10, 14)
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