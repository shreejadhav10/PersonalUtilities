package com.shree.personalutilities.views.todos

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shree.personalutilities.R

import com.shree.personalutilities.views.todos.dummy.DummyContent.DummyItem
import com.shree.personalutilities.views.todos.dummy.Task

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTodoRecyclerViewAdapter : RecyclerView.Adapter<MyTodoRecyclerViewAdapter.ViewHolder>() {
    private val values: MutableList<Task> = mutableListOf()

    fun setTaskList(tasks: List<Task>) {
        with(values) {
            clear()
            addAll(tasks)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_todo_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.taskName
        holder.contentView.text = item.taskCategory
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.txtTaskNameToDoItem)
        val contentView: TextView = view.findViewById(R.id.txtTaskCategoryToDoItem)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}