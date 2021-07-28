package com.shree.personalutilities.views.todos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shree.personalutilities.R
import com.shree.personalutilities.databinding.FragmentTodoListBinding
import com.shree.personalutilities.views.base.BaseFragment
import com.shree.personalutilities.views.todos.addtodo.AddToDoActivity


/**
 * A fragment representing a list of Tasks.
 */
class TodoFragment private constructor() : BaseFragment() {
    private var columnCount = 1
    private lateinit var binding: FragmentTodoListBinding;

    private val viewModel: ToDoViewModel by lazy {
        ViewModelProvider(this).get(ToDoViewModel::class.java)
    }

    private val TAG = "TodoFragment"

    override fun getActivityTitle(): String {
        return getString(R.string.todo_title)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentTodoListBinding>(
                inflater,
                R.layout.fragment_todo_list,
                container,
                false
        )
        binding.fabAddTODOList.setOnClickListener {
            val addToDoIntent = Intent(context, AddToDoActivity::class.java)
            startActivity(addToDoIntent)
        }

        // Set the adapter
        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            val dividerItemDecoration = DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
            adapter = MyTodoRecyclerViewAdapter()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewModel.taskList.observe(viewLifecycleOwner,{
           val adapter: MyTodoRecyclerViewAdapter? = binding.list.adapter as? MyTodoRecyclerViewAdapter
           adapter?.setTaskList(it)
       })

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                TodoFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}