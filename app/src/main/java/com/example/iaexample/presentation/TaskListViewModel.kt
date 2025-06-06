package com.example.iaexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iaexample.data.TaskEntity
import com.example.iaexample.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for listing pending tasks.
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

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

    /** Add a new task to the list. */
    fun addTask(title: String, description: String?) {
        viewModelScope.launch {
            repository.insert(TaskEntity(title = title, description = description))
        }
    }
}
