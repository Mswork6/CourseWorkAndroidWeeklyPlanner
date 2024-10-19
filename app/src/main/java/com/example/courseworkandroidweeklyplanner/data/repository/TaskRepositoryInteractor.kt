package com.example.courseworkandroidweeklyplanner.data.repository

import com.example.courseworkandroidweeklyplanner.data.taskData
import com.example.courseworkandroidweeklyplanner.domain.models.Task
import com.example.courseworkandroidweeklyplanner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class TaskRepositoryInteractor : TaskRepository {

    private val _tasks: MutableStateFlow<List<Task>> = MutableStateFlow(listOf())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()


    fun init() {
        _tasks.update { taskData }
    }

    override fun getData(): List<Task> = _tasks.value

    override fun addTask(task: Task) {
        _tasks.update {
            _tasks.value.plus(task)
        }

    }

    override fun updateTask(updatedTask: Task) {
        _tasks.update {
            it.map { task ->
                if (task.id == updatedTask.id) updatedTask else task
            }
        }
    }

    override fun deleteTask(taskId: UUID) {
        _tasks.update {
            _tasks.value.filter { it.id != taskId }
        }
    }

    override fun getTask(taskId: UUID): Task = _tasks.value.first { it.id == taskId }
}