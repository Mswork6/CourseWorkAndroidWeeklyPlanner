package com.example.courseworkandroidweeklyplanner.domain.repository

import com.example.courseworkandroidweeklyplanner.domain.models.Task
import java.util.UUID

interface TaskRepository {

    fun getData(): List<Task>

    fun addTask(task: Task)

    fun updateTask(updatedTask: Task)

    fun deleteTask(taskId: UUID)

    fun getTask(taskId: UUID): Task

}