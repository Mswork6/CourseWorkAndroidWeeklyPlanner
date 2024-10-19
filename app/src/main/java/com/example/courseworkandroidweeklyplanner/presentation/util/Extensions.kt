package com.example.courseworkandroidweeklyplanner.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.Instant
import java.time.LocalDate
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

@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val selectedDate = Instant.ofEpochMilli(utcTimeMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val today = LocalDate.now()
        return !selectedDate.isBefore(today)
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year >= LocalDate.now().year
    }
}

val NavBackStackEntry.lifecycleIsResumed: Boolean
    get() = this.lifecycle.currentState == Lifecycle.State.RESUMED