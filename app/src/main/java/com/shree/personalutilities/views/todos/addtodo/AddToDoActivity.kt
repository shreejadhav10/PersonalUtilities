package com.shree.personalutilities.views.todos.addtodo

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.shree.personalutilities.BaseActivity
import com.shree.personalutilities.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AddToDoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, AddToDoFragment.newInstance()).commit()
    }

    companion object {
        private const val TAG = "AddToDoActivity"
    }
}