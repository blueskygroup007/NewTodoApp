package com.bluesky.todoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author BlueSky
 * @date 23.10.4
 * Description:
 */
/*默认情况下，Room 将类名称用作数据库表名称。
如果您希望表具有不同的名称，请设置 @Entity 注解的 tableName 属性。同样，Room 默认使用字段名称作为数据库中的列名称。
如果您希望列具有不同的名称，请将 @ColumnInfo 注解添加到该字段并设置 name 属性。*/
@Entity(tableName = "todo_table")
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var priority: Priority,
    var title: String,
    var description: String
)
