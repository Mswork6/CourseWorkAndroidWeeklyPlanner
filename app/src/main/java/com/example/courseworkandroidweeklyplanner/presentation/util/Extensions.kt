package com.example.courseworkandroidweeklyplanner.presentation.util

import com.example.courseworkandroidweeklyplanner.model.WeekDates
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun dateToString(localDate: LocalDate): String =
    localDate.format(formatter)

fun dateToString(weekDates: WeekDates): String =
    "${dateToString(weekDates.weekStartDate)} - " +
            dateToString(weekDates.weekEndDate)