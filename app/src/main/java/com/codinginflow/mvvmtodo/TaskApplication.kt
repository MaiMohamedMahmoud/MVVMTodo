package com.codinginflow.mvvmtodo

import android.app.Application
import com.codinginflow.mvvmtodo.data.ToDoDatabase
import com.codinginflow.mvvmtodo.ui.task.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TaskApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database by lazy { ToDoDatabase.getDatabase(applicationContext) }
//    val repository by lazy { TaskRepository(database.taskDao()) }
}