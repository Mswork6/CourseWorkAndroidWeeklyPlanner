package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum
import com.example.courseworkandroidweeklyplanner.domain.models.SortStates
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates

class GetWeekDaysUseCase {
    operator fun invoke(weekDates: WeekDates, taskList: List<Task>, sort: SortStates): List<Day> {
        val daysOfWeek = mutableListOf<Day>()
        var currentDate = weekDates.weekStartDate
        var index = 0
        while (!currentDate.isAfter(weekDates.weekEndDate)) {
            val dayOfWeek = currentDate.dayOfWeek
            val dayName =
                DayEnum.entries.find { it.ordinal == dayOfWeek.ordinal }?.description ?: ""
            val tasks = taskList.filter { it.deadline == currentDate }
            val sortedTasks = sortTasks(tasks, sort)
            daysOfWeek.add(
                Day(
                    id = index,
                    name = dayName,
                    date = currentDate,
                    tasks = sortedTasks,
                    isExpanded = false
                )
            )
            index++
            currentDate = currentDate.plusDays(1)
        }
        return daysOfWeek
    }

    private fun sortTasks(taskList: List<Task>, sort: SortStates): List<Task> {
        return when (sort) {
            SortStates.INCREASE -> taskList.sortedBy { it.priority }
            SortStates.DECREASE -> taskList.sortedByDescending { it.priority }
            SortStates.STANDARD -> taskList
        }
    }

}