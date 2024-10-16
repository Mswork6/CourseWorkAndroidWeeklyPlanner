package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.Task

class UpdateWeekDaysUseCase {
    operator fun invoke(daysList: List<Day>, taskList: List<Task>): List<Day> {
        val daysOfWeek = mutableListOf<Day>()

        for (day in daysList) {
            val tasks = taskList.filter { it.deadline == day.date }
            daysOfWeek.add(day.copy(tasks = tasks))
        }

        return daysOfWeek
    }

}
