package personal.project.workmanagerassignment

import androidx.room.TypeConverter
import java.util.*

class Convertors {

    @TypeConverter
    fun dataToLong(value: Date): Long{
        return value.time
    }

    @TypeConverter
    fun LongToDate(value: Long): Date{
        return Date(value)
    }

}