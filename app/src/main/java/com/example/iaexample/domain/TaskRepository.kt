package com.example.iaexample.domain

import com.example.iaexample.data.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing tasks.
 */
interface TaskRepository {
    /** Stream of tasks that are not yet completed. */
    fun getPendingTasks(): Flow<List<TaskEntity>>

    suspend fun insert(task: TaskEntity)
    suspend fun update(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
}
