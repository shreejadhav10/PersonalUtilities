package com.shree.personalutilities.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shree.personalutilities.views.todos.dummy.Task

class TaskRepository(private val taskDao: TaskDao){

    suspend fun insertTask(taskName: String, taskCategory: String?, scheduledOn: String): Long {
        val task = Task(
            taskName = taskName,
            taskCategory = taskCategory,
            scheduledOn = scheduledOn
        )
        return  taskDao.insert(task);
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAll();
    }
}