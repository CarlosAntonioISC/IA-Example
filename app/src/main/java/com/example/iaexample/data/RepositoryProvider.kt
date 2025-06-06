package com.example.iaexample.data

import android.content.Context
import androidx.room.Room

/**
 * Provides database and repository instances.
 */
object RepositoryProvider {
    private var database: AppDatabase? = null

    fun provideRepository(context: Context): TaskRepositoryImpl {
        val db = database ?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "tasks.db"
        ).build().also { database = it }
        return TaskRepositoryImpl(db.taskDao())
    }
}
