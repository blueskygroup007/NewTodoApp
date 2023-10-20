package com.bluesky.todoapp.data

import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.fragment.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * BindingAdapter用于自定义xml中的控件属性.主要用途是用代码来实现数据的转换(例如int转换成color)
 * */
class BindingAdapter {

    companion object {
        /* Todo 该注解需要在model.gradle中添加kotlin-kapt插件(id("kotlin-kapt"))
        * 使用dataBinding的自定义属性设置点击事件
        * */
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic /*Todo 生成全局静态方法，而非内部静态方法，因为是在伴生对象中定义*/
        fun navigationToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener() {
                view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
            }
        }

        @BindingAdapter("android:nodatatip")
        @JvmStatic
        fun noDataTip(view: View, dataCount: MutableLiveData<Int>) {

            /*if (dataCount.value != null) {
                if (dataCount.value!! > 0) {
                    view.visibility = View.INVISIBLE
                }
            } else {
                view.visibility = View.VISIBLE
            }*/
            /*使用下面这种方式判断，空值时赋值*/
            val empty: Int = dataCount.value ?: 0
            view.visibility = if (empty != 0) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToColor")
        @JvmStatic
        fun parsePriorityToColor(cardView: CardView, priority: Priority) {
            when (priority) {
                Priority.HIGH -> cardView.setCardBackgroundColor(
                    cardView.context.resources.getColor(
                        R.color.red
                    )
                )

                Priority.MEDIUM -> cardView.setCardBackgroundColor(
                    cardView.context.resources.getColor(
                        R.color.yellow
                    )
                )

                Priority.LOW -> cardView.setCardBackgroundColor(
                    cardView.context.resources.getColor(
                        R.color.green
                    )
                )

            }
        }

        @BindingAdapter("android:navigateListItemToUpdate")
        @JvmStatic
        fun navigateListItemToUpdate(layout: ConstraintLayout, toDoData: ToDoData) {
            layout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(toDoData)
                layout.findNavController().navigate(action)
            }
        }
    }
}