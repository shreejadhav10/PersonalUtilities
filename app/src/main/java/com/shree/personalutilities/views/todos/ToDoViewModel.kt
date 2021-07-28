package com.shree.personalutilities.views.todos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.shree.personalutilities.data.database.AppDatabase
import com.shree.personalutilities.data.repos.TaskRepository
import com.shree.personalutilities.views.todos.dummy.Task
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository: TaskRepository

    /**
     * An array of task items.
     */
     var taskList: MediatorLiveData<List<Task>> = MediatorLiveData()

    init {
        val taskDao = AppDatabase.getInstance(application).taskDao()
        taskRepository = TaskRepository(taskDao)
        viewModelScope.launch { getAllTasks()}

    }

    private fun getAllTasks() {
        viewModelScope.launch {
            taskList.addSource(taskRepository.getAllTasks()) {
                taskList.value = it;
            }
        }
    }

}