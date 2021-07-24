package com.shree.personalutilities.views.todos.addtodo

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.shree.personalutilities.data.database.AppDatabase
import com.shree.personalutilities.data.repos.TaskRepository
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class AddToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository: TaskRepository

    companion object {
        private const val TAG = "AddToDoViewModel"
    }

    private var _taskName: MutableLiveData<String> = MutableLiveData()
    val taskName = _taskName

    private var _taskDate: MutableLiveData<Calendar> = MutableLiveData()
    val taskDate = _taskDate

    private var _taskCategory: MutableLiveData<String> = MutableLiveData()
    val taskCategory = _taskCategory

    val messageLiveData = MutableLiveData<Map<Int, String>>()

    private val taskDateString: String
        @SuppressLint("SimpleDateFormat")
        get() {
            _taskDate.value!!.time.let {
                val df = SimpleDateFormat("yyyy:mm:dd")
                return df.format(it)
            }
        }

    init {
        val taskDao = AppDatabase.getInstance(application).taskDao()
        taskRepository = TaskRepository(taskDao)
        _taskDate.value = Calendar.getInstance();
        Log.d(TAG, "Default date is: ${Calendar.getInstance().time}")
    }

    fun onDateChanged(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        with(_taskDate.value) {
            val oldYear = this?.get(Calendar.YEAR)
            val oldMonth = this?.get(Calendar.MONTH)
            val oldDay = this?.get(Calendar.DAY_OF_MONTH)
            if (oldYear != year || oldMonth != monthOfYear || oldDay != dayOfMonth) {
                _taskDate.value?.set(year, monthOfYear, dayOfMonth)
                Log.d(TAG, "onDateChanged: $year, $monthOfYear, $dayOfMonth")
            }
        }

    }

    fun onSaveClick() {
        if (isValid()) {
            var job = viewModelScope.launch(Dispatchers.IO) {
                saveTask()
                runBlocking {
                    withContext(Dispatchers.Main) {
                        messageLiveData.value=(mapOf(101 to "Task Added successfully"))
                    }
                }

            }
            return
        } else {
            messageLiveData.value = mapOf(Pair(102, "Please fill all the details"))
        }
    }


    private suspend fun saveTask() {
        taskRepository.insertTask(
            taskName = taskName.value!!,
            taskCategory = taskCategory.value,
            scheduledOn = taskDateString
        )
    }

    private fun isValid(): Boolean =
        !(taskName.value.isNullOrEmpty() && taskCategory.value.isNullOrEmpty())

}
