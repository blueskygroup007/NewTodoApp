package com.bluesky.todoapp.data.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.bluesky.todoapp.data.models.Priority

/**
 * viewmodel的公共方法放在这里
 */
class SharedViewModel(application: Application):AndroidViewModel(application) {
     fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

     fun parsePriority(priority: Int): Priority {
        return when (priority) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUM
            2 -> Priority.LOW
            else -> Priority.LOW
        }
    }
}