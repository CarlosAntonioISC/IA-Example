package com.example.iaexample.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Application database holding task entities.
 */
@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
