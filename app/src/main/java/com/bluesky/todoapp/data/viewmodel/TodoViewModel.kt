package com.bluesky.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
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
    val allTodoData: LiveData<List<ToDoData>>
    val todoDataCount : MutableLiveData<Int> =MutableLiveData(0)

    init {
        todoDao = ToDoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodoData = repository.getAllData
        //todoDataCount.value=allTodoData.value?.size
    }

    fun insertData(data: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data)
        }
    }

    fun updateData(data: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(data)
        }
    }

    fun deleteData(data: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(data)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}