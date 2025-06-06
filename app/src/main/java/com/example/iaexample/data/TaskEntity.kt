package com.example.iaexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a to-do task.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val completed: Boolean = false
)
