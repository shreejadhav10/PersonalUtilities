package com.shree.personalutilities.views.todos.addtodo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.shree.personalutilities.R
import com.shree.personalutilities.databinding.AddToDoFragmentBinding
import com.shree.personalutilities.views.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToDoFragment private constructor() : BaseFragment() {
    private var binding: AddToDoFragmentBinding? = null

    companion object {
        fun newInstance() = AddToDoFragment()
    }

    private val viewModel: AddToDoViewModel by lazy {
        ViewModelProvider(this).get(AddToDoViewModel::class.java)
    }

    override fun getActivityTitle(): String = getString(R.string.add_task_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        initErrorListener()
    }

    private fun initErrorListener() {
        viewModel.messageLiveData.observe(
            this,
            Observer { messageMap ->
                messageMap.mapKeys { key ->
                    when (key.key) {
                        101 -> {
                            messageMap[key.key]?.let {
                                showSnackBar(it)
                                lifecycleScope.launch {
                                    withContext(Dispatchers.IO) {
                                        delay(200)
                                        this@AddToDoFragment.activity?.finish()
                                    }
                                }
                            }
                        }
                        else -> messageMap[key.key]?.let {
                            showSnackBar(it)
                        }
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<AddToDoFragmentBinding>(
            inflater,
            R.layout.add_to_do_fragment,
            container,
            false
        ).apply {
            lifecycleOwner = this@AddToDoFragment
            addTaskViewModel = viewModel
        }
        return binding?.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveMenuAddTodo -> {
                validateForm()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateForm() {
        viewModel.onSaveClick()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_to_do_menus, menu);
    }

    private fun showSnackBar(message: String) =
        binding?.root?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }


}