package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates

class GetWeekDaysUseCase {
    operator fun invoke(weekDates: WeekDates, taskList: List<Task>): List<Day> {
        val daysOfWeek = mutableListOf<Day>()
        var currentDate = weekDates.weekStartDate
        var index = 0
        while (!currentDate.isAfter(weekDates.weekEndDate)) {
            val dayOfWeek = currentDate.dayOfWeek
            val dayName =
                DayEnum.entries.find { it.ordinal == dayOfWeek.ordinal }?.description ?: ""
            val tasks = taskList.filter { it.deadline == currentDate }
            //tasks.sortedBy { it.priority }
            daysOfWeek.add(
                Day(
                    id = index,
                    name = dayName,
                    date = currentDate,
                    tasks = tasks,
                    isExpanded = false
                )
            )
            index++
            currentDate = currentDate.plusDays(1)
        }
        return daysOfWeek
    }

}