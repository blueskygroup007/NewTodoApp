package com.bluesky.todoapp.fragment.list

import androidx.recyclerview.widget.DiffUtil
import com.bluesky.todoapp.data.models.ToDoData

/**
 *
 * @author BlueSky
 * @date 23.10.24
 * Description:
 */
class TodoDiffUtil(private val oldList: List<ToDoData>, private val newList: List<ToDoData>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        //Todo Kotlin 中的操作符 === 用于比较对象的引用是否指向同一个对象，运行时如果是基本数据类型 === 等价于 ==
        //return oldList[oldItemPosition] === newList[newItemPosition]

        //视频中使用上面的方式判断两个对象的引用是否相同.这里采用比较对象的id
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        //Todo 在kotlin中,为string的==操作重载了equals.
        if (oldItem.id == newItem.id
            && oldItem.title == newItem.title
            && oldItem.description == newItem.description
            && oldItem.priority == newItem.priority
        ) {
            return true
        }
        return false
    }
}