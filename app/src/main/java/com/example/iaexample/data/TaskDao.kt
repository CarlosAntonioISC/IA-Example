package com.example.iaexample.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for tasks.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE completed = 0 ORDER BY createdAt ASC")
    fun getPendingTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}
