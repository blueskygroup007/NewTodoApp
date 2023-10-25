package com.bluesky.todoapp.fragment.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.databinding.ItemRecyclerListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.TodoHolder>() {

    var dataList = emptyList<ToDoData>()

    class TodoHolder(val binding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        /* Todo 改为databinding,在holder.from()中实现

        *//*Todo 重点：容易出现item不能充满宽度。原因：binding必须用三个参数的inflate方法获取。*//*
        //val inflate =LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_list, parent, false)
        //val itemBinding = ItemRecyclerListBinding.inflate(LayoutInflater.from(parent.context))
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRecyclerListBinding.inflate(inflater, parent, false)
        return TodoHolder(itemBinding)*/

        return TodoHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {


        /*holder.binding.tvTitle.text = dataList[position].title
        holder.binding.tvDescription.text = dataList[position].description
        holder.binding.root.setOnClickListener {
            //普通传参方式
    *//*      val bundle = Bundle()
            bundle.putSerializable("TodoData", dataList[position])
            holder.itemBinding.root.findNavController()
            .navigate(R.id.action_listFragment_to_updateFragment, bundle)*//*

            //navigation的传参方式
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.binding.root.findNavController().navigate(action)
        }
        when (dataList[position].priority) {
            Priority.HIGH -> holder.binding.cvPriorityIndicator.setCardBackgroundColor(
                holder.binding.root.context.resources.getColor(
                    R.color.red
                )
            )

            Priority.MEDIUM -> holder.binding.cvPriorityIndicator.setCardBackgroundColor(
                holder.binding.root.context.resources.getColor(
                    R.color.yellow
                )
            )

            Priority.LOW -> holder.binding.cvPriorityIndicator.setCardBackgroundColor(
                holder.binding.root.context.resources.getColor(
                    R.color.green
                )
            )

        }*/

        /*采用databinding方式*/
        holder.bind(dataList[position])
    }

    fun setData(data: List<ToDoData>) {
        /* Todo 第一次刷新时,data=null.因此加上判断.只有当data有数据时,才使用DiffUtil
        * */
        Log.d("setdata:", "olddata=${dataList}  newdata=${data}")
        if (dataList.isNotEmpty()) {
            val todoDiffUtil = TodoDiffUtil(dataList, data)
            val todoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)
            todoDiffResult.dispatchUpdatesTo(this)
            dataList = data
        } else {
            dataList = data
            notifyDataSetChanged()
        }
    }
}