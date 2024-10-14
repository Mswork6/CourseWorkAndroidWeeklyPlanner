package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class GetCurrentWeekUseCase {

    fun invoke() : WeekDates {
        val today = LocalDate.now()
        val firstDayOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val lastDayOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        return WeekDates(weekStartDate = firstDayOfWeek, weekEndDate = lastDayOfWeek)
    }
}