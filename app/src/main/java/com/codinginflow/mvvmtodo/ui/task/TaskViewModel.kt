package com.codinginflow.mvvmtodo.ui.task

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codinginflow.mvvmtodo.data.Task
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepo: TaskRepository) : ViewModel() {

    val tasks: LiveData<List<Task>> = taskRepo.getAllTask().asLiveData()

    //this function has to be edited...
    fun insert() = viewModelScope.launch {
        Log.i("TaskViewModel", "insert")
        taskRepo.insert(Task("Do Laundry", important = true))
        taskRepo.insert(Task("Wipe floor", completed = true))
        taskRepo.insert(Task("Clean .."))
        taskRepo.insert(Task("Do your homework"))
        taskRepo.insert(Task("Cook"))
        taskRepo.insert(Task("Call your mom", completed = true))
        taskRepo.insert(Task("Go to superMarket", important = true))
    }

}