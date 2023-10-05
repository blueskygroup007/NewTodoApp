package com.bluesky.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.bluesky.todoapp.data.ToDoDao
import com.bluesky.todoapp.data.models.ToDoData

/**
 *
 * @author BlueSky
 * @date 23.10.5
 * Description:
 */
class TodoRepository(val todoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = todoDao.getAllData()

    /*里面的insertTodo是suspend方法,因此该方法也要是suspend*/
    suspend fun insertData(toDoData: ToDoData) {
        todoDao.insertTodo(toDoData)
    }
}