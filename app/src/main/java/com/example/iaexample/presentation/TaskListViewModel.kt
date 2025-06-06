package com.example.iaexample.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.iaexample.data.RepositoryProvider
import com.example.iaexample.data.TaskEntity
import com.example.iaexample.domain.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for listing pending tasks.
 */
class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository = RepositoryProvider.provideRepository(application)

    /** Stream of pending tasks exposed to the UI. */
    val tasks: StateFlow<List<TaskEntity>> =
        repository.getPendingTasks()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    /** Mark a task as completed. */
    fun toggleCompleted(task: TaskEntity, completed: Boolean) {
        viewModelScope.launch {
            repository.update(task.copy(completed = completed))
        }
    }

    /** Delete a task permanently. */
    fun delete(task: TaskEntity) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}
