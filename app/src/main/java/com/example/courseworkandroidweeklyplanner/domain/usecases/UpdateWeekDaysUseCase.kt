package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.SortStates
import com.example.courseworkandroidweeklyplanner.domain.models.Task

class UpdateWeekDaysUseCase {
    operator fun invoke(daysList: List<Day>, taskList: List<Task>, sort: SortStates): List<Day> {
        val daysOfWeek = mutableListOf<Day>()

        for (day in daysList) {
            val tasks = taskList.filter { it.deadline == day.date }
            val sortedTasks = sortTasks(tasks, sort)
            daysOfWeek.add(day.copy(tasks = sortedTasks))
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
