package com.example.courseworkandroidweeklyplanner.data

import com.example.courseworkandroidweeklyplanner.model.Day
import com.example.courseworkandroidweeklyplanner.model.DayEnum.*
import java.time.LocalDate


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