package com.example.courseworkandroidweeklyplanner.domain.usecases

import com.example.courseworkandroidweeklyplanner.domain.models.Day

class ChangeExpandDayCardUseCase {

    operator fun invoke(day: Day): Day {
        return Day(
            id = day.id,
            name = day.name,
            date = day.date,
            isExpanded = !day.isExpanded,
            tasks = day.tasks
        )
    }
}