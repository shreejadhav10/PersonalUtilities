package com.shree.personalutilities.data.repos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shree.personalutilities.views.todos.dummy.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id IN (:taskIds)")
    suspend fun loadAllByIds(taskIds: IntArray): List<Task>

    @Insert
    suspend fun insert(task: Task): Long


    @Insert
    suspend fun insertAll(vararg tasks: Task)

    @Delete
    suspend fun delete(task: Task)
}