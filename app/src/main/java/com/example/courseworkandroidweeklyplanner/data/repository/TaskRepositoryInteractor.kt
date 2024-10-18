package com.example.courseworkandroidweeklyplanner.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.courseworkandroidweeklyplanner.data.taskData
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.repository.TaskRepository
import java.util.UUID

class TaskRepositoryInteractor : TaskRepository {

    val tasks: SnapshotStateList<Task> = mutableStateListOf()

    fun init() {
        for (task in taskData) {
            tasks.add(task)
        }
    }

    override fun getData(): List<Task> = tasks

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }

    override fun deleteTask(taskId: UUID) {
        tasks.removeAll { it.id == taskId }
    }

    override fun getTask(taskId: UUID): Task = tasks.first { it.id == taskId }


}