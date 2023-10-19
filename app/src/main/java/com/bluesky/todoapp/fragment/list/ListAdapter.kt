package com.bluesky.todoapp.fragment.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.viewmodel.TodoViewModel
import com.bluesky.todoapp.databinding.ItemRecyclerListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.TodoHolder>() {

    private var dataList = emptyList<ToDoData>()

    class TodoHolder(val itemBinding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        /*Todo 重点：容易出现item不能充满宽度。原因：binding必须用三个参数的inflate方法获取。*/
        //val inflate =LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_list, parent, false)
        //val itemBinding = ItemRecyclerListBinding.inflate(LayoutInflater.from(parent.context))
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRecyclerListBinding.inflate(inflater, parent, false)
        return TodoHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        Log.d("data======", dataList.toString())
        holder.itemBinding.tvTitle.text = dataList[position].title
        holder.itemBinding.tvDescription.text = dataList[position].description
        holder.itemBinding.root.setOnClickListener {
            //普通传参方式
/*            val bundle = Bundle()
            bundle.putSerializable("TodoData", dataList[position])
            holder.itemBinding.root.findNavController()
                .navigate(R.id.action_listFragment_to_updateFragment, bundle)*/
            //navigation的传参方式
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemBinding.root.findNavController().navigate(action)
        }
        when (dataList[position].priority) {
            Priority.HIGH -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.red
                )
            )

            Priority.MEDIUM -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.yellow
                )
            )

            Priority.LOW -> holder.itemBinding.cvPriorityIndicator.setCardBackgroundColor(
                holder.itemBinding.root.context.resources.getColor(
                    R.color.green
                )
            )

        }
    }

    fun setData(data: List<ToDoData>) {
        dataList = data
        notifyDataSetChanged()
    }
}