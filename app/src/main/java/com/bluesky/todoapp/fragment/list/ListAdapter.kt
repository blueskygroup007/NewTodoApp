package com.bluesky.todoapp.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.viewmodel.TodoViewModel
import com.bluesky.todoapp.databinding.ItemRecyclerListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.TodoHolder>() {

    var dataList = emptyList<ToDoData>()

    class TodoHolder(val itemBinding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_list, parent, false)
        val itemBinding = ItemRecyclerListBinding.inflate(LayoutInflater.from(parent.context))
        return TodoHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        //holder.itemView.findViewById<TextView>(R.id.tv_title).text=dataList[position].title
        holder.itemBinding.tvTitle.text = dataList[position].title
        holder.itemBinding.tvDescription.text = dataList[position].description
        when (dataList[position].priority) {
            Priority.HIGH -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.red
                )
            )

            Priority.MEDIUM -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.red
                )
            )

            Priority.LOW -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.red
                )
            )

        }
    }

    fun setData(data: List<ToDoData>) {
        dataList = data
        notifyDataSetChanged()
    }
}