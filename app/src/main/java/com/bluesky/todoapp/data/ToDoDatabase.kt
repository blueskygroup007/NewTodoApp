package com.bluesky.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bluesky.todoapp.data.models.ToDoData

/**
 *
 * @author BlueSky
 * @date 23.10.4
 * Description:
 */
@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object {
        /*Kotlin 中的 volatile 关键字用于修饰变量，表示该变量在多线程环境下是共享的。当一个线程修改了这个变量的值时，其他线程能立刻看到这个更新的值。*/
        @Volatile
        var INSTANCE: ToDoDatabase? = null


        fun getDatabase(context: Context): ToDoDatabase {
            /*编译器报错,因为kotlin认为INSTANCE变量在判断不为空后,多线程情况下,可能被其他线程修改为空(报错信息中mutable的意思),因此改为下方写法*/
            /*if (INSTANCE != null) {
                return INSTANCE
            }*/

            /*放到临时变量里转存一下(var或val都可)*/
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val tempInstance =
                    Room.databaseBuilder(context, ToDoDatabase::class.java, "todo.db").build()
                INSTANCE = tempInstance
                return tempInstance
            }
        }
    }
}