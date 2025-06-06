package com.example.iaexample.data

import com.example.iaexample.domain.TaskRepository
import kotlinx.coroutines.flow.Flow

/**
 * Room-backed implementation of [TaskRepository].
 */
class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {
    override fun getPendingTasks(): Flow<List<TaskEntity>> = dao.getPendingTasks()

    override suspend fun insert(task: TaskEntity) = dao.insertTask(task)

    override suspend fun update(task: TaskEntity) = dao.updateTask(task)

    override suspend fun delete(task: TaskEntity) = dao.deleteTask(task)
}
