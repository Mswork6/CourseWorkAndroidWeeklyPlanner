package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class GetWeekUseCase {
    operator fun invoke(date: LocalDate) : WeekDates {
        val firstDayOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val lastDayOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        return WeekDates(weekStartDate = firstDayOfWeek, weekEndDate = lastDayOfWeek)
    }
}