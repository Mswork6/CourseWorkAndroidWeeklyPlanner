package com.example.courseworkandroidweeklyplanner.presentation.util

import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun dateToString(localDate: LocalDate): String = localDate.format(dateFormatter)

fun timeToString(time: LocalTime): String = time.format(timeFormatter)

fun convertToLocalDate(dateMillis: Long): LocalDate = Instant
    .ofEpochMilli(dateMillis)
    .atZone(ZoneId.systemDefault())
    .toLocalDate()

fun dateToString(weekDates: WeekDates): String =
    "${dateToString(weekDates.weekStartDate)} - " +
            dateToString(weekDates.weekEndDate)