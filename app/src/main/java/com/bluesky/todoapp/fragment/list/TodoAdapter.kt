package com.bluesky.todoapp.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.databinding.ItemRecyclerListBinding

/**
 *
 * @author BlueSky
 * @date 23.10.24
 * Description:
 */
class TodoAdapter : ListAdapter<ToDoData, TodoAdapter.TodoHolder>(TodoDiffCallback()) {
    class TodoHolder(val binding: ItemRecyclerListBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): TodoHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemRecyclerListBinding.inflate(inflater, parent, false)
                return TodoHolder(itemBinding)
            }
        }

        fun bind(todoData: ToDoData) {
            binding.todoData = todoData
            binding.executePendingBindings()//当你的数据改变时，数据绑定在一个动画帧之前刷新，executePendingBindings()可以立即强制刷新，此操作必须在UI线程进行
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoHolder {
        return TodoHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<ToDoData>() {
    override fun areItemsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
        return oldItem == newItem

    }

}
