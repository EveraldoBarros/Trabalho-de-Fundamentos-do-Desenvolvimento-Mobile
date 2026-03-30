package com.taskflow.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val listener: TaskListener) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiff()) {

    interface TaskListener {
        fun onEdit(task: Task)
        fun onDelete(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.textTaskTitle)
        private val descriptionView: TextView = itemView.findViewById(R.id.textTaskDescription)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEditTask)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDeleteTask)

        fun bind(task: Task) {
            titleView.text = task.title
            descriptionView.text = task.description

            btnEdit.setOnClickListener {
                listener.onEdit(task)
            }
            btnDelete.setOnClickListener {
                listener.onDelete(task)
            }
        }
    }

    class TaskDiff : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
