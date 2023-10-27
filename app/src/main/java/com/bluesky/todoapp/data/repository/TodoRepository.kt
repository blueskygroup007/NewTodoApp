package com.bluesky.todoapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bluesky.todoapp.data.ToDoDao
import com.bluesky.todoapp.data.models.ToDoData

/**
 *
 * @author BlueSky
 * @date 23.10.5
 * Description:
 */
class TodoRepository(val todoDao: ToDoDao) {

    /* Todo 思考以下，为什么这里的livedata要在repository这里就提前获取，而不担心后续数据变化了*/
    val getAllData: LiveData<List<ToDoData>> = todoDao.getAllData()
    val sortByHighPriority = todoDao.sortByHighPriority()
    val sortByLowPriority = todoDao.sortByLowPriority()

    /*里面的insertTodo是suspend方法,因此该方法也要是suspend*/
    suspend fun insertData(toDoData: ToDoData) {
        todoDao.insertTodo(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData) {
        todoDao.updateTodo(toDoData)
    }

    suspend fun deleteData(toDoData: ToDoData) {
        todoDao.delete(toDoData)
    }

    suspend fun deleteAll() {
        todoDao.deleteAll()
    }

    fun searchDatabase(searchKey: String): LiveData<List<ToDoData>> {
        return todoDao.searchDatabase(searchKey)
    }
}