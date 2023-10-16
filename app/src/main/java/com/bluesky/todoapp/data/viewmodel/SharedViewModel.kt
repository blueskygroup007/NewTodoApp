package com.bluesky.todoapp.data.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.AndroidViewModel
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority

/**
 * viewmodel的公共方法放在这里
 */
class SharedViewModel(application: Application):AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener=object :OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0-> (parent?.get(0) as TextView).setTextColor(application.getColor(R.color.red))
                1-> (parent?.get(0) as TextView).setTextColor(application.getColor(R.color.yellow))
                2-> (parent?.get(0) as TextView).setTextColor(application.getColor(R.color.green))
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }
     fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

     fun parseIntToPriority(priority: Int): Priority {
        return when (priority) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUM
            2 -> Priority.LOW
            else -> Priority.LOW
        }
    }
    fun parsePriorityToInt(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}