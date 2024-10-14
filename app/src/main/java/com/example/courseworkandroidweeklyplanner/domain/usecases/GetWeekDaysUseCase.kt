package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates

class GetWeekDaysUseCase {
    fun invoke(weekDates: WeekDates): List<Day> {
        val daysOfWeek = mutableListOf<Day>()
        var currentDate = weekDates.weekStartDate
        while (!currentDate.isAfter(weekDates.weekEndDate)) {
            val dayOfWeek = currentDate.dayOfWeek
            val dayName = DayEnum.entries.find { it.ordinal == dayOfWeek.ordinal }?.description ?: ""
            daysOfWeek.add(
                Day(
                    name = dayName,
                    date = currentDate,
                    tasks = listOf()
                )
            )
            currentDate = currentDate.plusDays(1)
        }
        return daysOfWeek
    }

}