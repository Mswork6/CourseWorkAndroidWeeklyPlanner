package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.SortStateEnum
import com.example.courseworkandroidweeklyplanner.domain.models.Task

class UpdateWeekDaysUseCase {
    operator fun invoke(daysList: List<Day>, taskList: List<Task>, sort: SortStateEnum): List<Day> {
        val daysOfWeek = mutableListOf<Day>()

        for (day in daysList) {
            val tasks = taskList.filter { it.deadline == day.date }
            val sortedTasks = sortTasks(tasks, sort)
            daysOfWeek.add(day.copy(tasks = sortedTasks))
        }

        return daysOfWeek
    }

    private fun sortTasks(taskList: List<Task>, sort: SortStateEnum): List<Task> {
        return when (sort) {
            SortStateEnum.INCREASE -> taskList.sortedBy { it.priority }
            SortStateEnum.DECREASE -> taskList.sortedByDescending { it.priority }
            SortStateEnum.STANDARD -> taskList
        }
    }

}
