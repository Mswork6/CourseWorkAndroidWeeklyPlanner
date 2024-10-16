package com.example.courseworkandroidweeklyplanner.data

import com.example.courseworkandroidweeklyplanner.domain.models.Day
import com.example.courseworkandroidweeklyplanner.domain.models.DayEnum.*
import com.example.courseworkandroidweeklyplanner.domain.models.Priority
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.models.WeekDates
import java.time.LocalDate
import java.util.UUID

val weekDates = WeekDates(LocalDate.of(2024, 10, 7),
    LocalDate.of(2024, 10, 14))

val taskData : List<Task> = mutableListOf(
    Task(
        id = UUID.randomUUID(),
        name = "Сделать функциональный анализ, который запланирован на 25 число этого месяца в " +
                "девятнадцать часов вечера",
        description = "",
        deadline = LocalDate.of(2024, 10, 18),
        priority = Priority.HIGH,
        notification = true,
        notificationTime = null,
        isDone = false
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Сходить в зал",
        description = "",
        deadline = LocalDate.of(2024, 10, 16),
        priority = Priority.BASIC,
        notification = false,
        notificationTime = null,
        isDone = true
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Посетить университет",
        description = "",
        deadline = LocalDate.of(2024, 10, 16),
        priority = Priority.LOW,
        notification = true,
        notificationTime = null,
        isDone = true
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Реализовать анимацию карточки для проекта",
        description = "",
        deadline = LocalDate.of(2024, 10, 16),
        priority = Priority.HIGH,
        notification = false,
        notificationTime = null,
        isDone = true
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Прогуляться в парке",
        description = "",
        deadline = LocalDate.of(2024, 10, 8),
        priority = Priority.BASIC,
        notification = false,
        notificationTime = null,
        isDone = true
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Сходить в зал",
        description = "",
        deadline = LocalDate.of(2024, 10, 10),
        priority = Priority.HIGH,
        notification = true,
        notificationTime = null,
        isDone = true
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Сходить в зал",
        description = "",
        deadline = LocalDate.of(2024, 10, 22),
        priority = Priority.LOW,
        notification = false,
        notificationTime = null,
        isDone = false
    ),

    Task(
        id = UUID.randomUUID(),
        name = "Посетить универ",
        description = "",
        deadline = LocalDate.of(2024, 10, 25),
        priority = Priority.HIGH,
        notification = true,
        notificationTime = null,
        isDone = false
    ),
)
