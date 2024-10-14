package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.LocalDate
import java.util.UUID

class GetWeekDaysUseCase {
    fun invoke(weekDates: WeekDates): List<Day> {
        val daysOfWeek = mutableListOf<Day>()
        var currentDate = weekDates.weekStartDate
        var index = 0
        while (!currentDate.isAfter(weekDates.weekEndDate)) {
            val dayOfWeek = currentDate.dayOfWeek
            val dayName = DayEnum.entries.find { it.ordinal == dayOfWeek.ordinal }?.description ?: ""
            daysOfWeek.add(
                Day(
                    id = index,
                    name = dayName,
                    date = currentDate,
                    tasks = listOf(
                        Task(
                            id = UUID.randomUUID(),
                            name = "Сделать Джаву",
                            description = "Сдать до 25 числа",
                            deadline = LocalDate.of(2024, 10, 25),
                            priority = Priority.HIGH,
                            notification = true,
                            isDone = false
                        )
                    ),
                    isExpanded = false
                )
            )
            index++
            currentDate = currentDate.plusDays(1)
        }
        return daysOfWeek
    }

}