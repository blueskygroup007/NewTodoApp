package com.bluesky.todoapp.data

import androidx.room.TypeConverter
import com.bluesky.todoapp.data.models.Priority

/**
 *
 * @author BlueSky
 * @date 23.10.5
 * Description:Room不支持对象引用,如果需要将自定义类型存储在数据库中,就必须提供类型转换器.
 * 1.生成转换器类,提供转换方法,添加@TypeConverter注解
 * 2.在DataBase类中添加@TypeConverters注解引用转换器类
 */
class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}