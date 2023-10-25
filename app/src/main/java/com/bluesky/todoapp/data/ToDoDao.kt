package com.bluesky.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bluesky.todoapp.data.models.ToDoData

/**
 *
 * @author BlueSky
 * @date 23.10.4
 * Description:
 */
@Dao
interface ToDoDao {
    /* 不加suspend是因为返回livedata的方法自动异步执行*/
    /*order by id ASC 表示按id排序,ASC表示升序(默认值)*/
    @Query("select * from todo_table order by id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    /*加上suspend关键字,表示该方法将会使用协程在后台执行,这是一个好习惯*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(data: ToDoData)

    @Update
    suspend fun updateTodo(data: ToDoData)

    @Query("Delete from todo_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(data: ToDoData)

    @Query("select * from todo_table order by priority")
    fun getAllDataFromLow(): LiveData<List<ToDoData>>

    @Query("select * from todo_table where title like :searchKey")
    fun searchDatabase(searchKey: String): LiveData<List<ToDoData>>
}