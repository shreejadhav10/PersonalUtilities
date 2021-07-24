package com.shree.personalutilities.views.todos.dummy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "task_category") val taskCategory: String?,
    @ColumnInfo(name = "scheduled_on") val scheduledOn: String = ""
)
