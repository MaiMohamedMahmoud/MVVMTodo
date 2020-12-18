package com.codinginflow.mvvmtodo.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtodo.R
import com.codinginflow.mvvmtodo.data.Task
import com.codinginflow.mvvmtodo.databinding.ItemTaskBinding
import kotlinx.android.synthetic.main.item_task.view.*

/**
 * ListAdapter we pass completely new list and
 * listAdpter will calculate the change between the new and the old list
 * so we need to have a callback DifUtils to say how to compute while compairing the two list
 */
class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskListViewHolder>(DiffCallback()) {
    class TaskListViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.apply {
                textViewName.text = item.name
                checkBoxCompleted.isChecked = item.completed
                textViewName.paint.isStrikeThruText = item.completed
                labelPriority.isVisible = item.important
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val binding = ItemTaskBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem == newItem


    }
}