package com.codinginflow.mvvmtodo.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtodo.R
import com.codinginflow.mvvmtodo.TaskApplication
import com.codinginflow.mvvmtodo.data.ToDoDatabase
import com.codinginflow.mvvmtodo.databinding.FragmentTasksBinding

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false)
        Log.i("TaskViewModel", "observess")
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val database = ToDoDatabase.getDatabase(application)

        val viewModel: TaskViewModel by viewModels() {
            TaskViewModelFactory(TaskRepository(database.taskDao()))
        }
        val adapter = TaskListAdapter()
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.insert()
        //here observe the taskList
        viewModel.tasks.observe(viewLifecycleOwner, Observer { taskList ->
            adapter.submitList(taskList)
        })
        binding.apply {
            Log.i("TaskViewModel", "binding")
            recyclerViewTasks.adapter = adapter
            recyclerViewTasks.layoutManager = manager
        }
    }
}