package com.bluesky.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bluesky.todoapp.data.ToDoDao
import com.bluesky.todoapp.data.ToDoDatabase
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    val todoDao: ToDoDao
    val repository: TodoRepository
    val todoData: LiveData<List<ToDoData>>

    init {
        todoDao = ToDoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        todoData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }
}