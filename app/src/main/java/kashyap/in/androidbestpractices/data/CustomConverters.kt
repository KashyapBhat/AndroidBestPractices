package kashyap.`in`.androidbestpractices.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CustomConverters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType =
            object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringInteger(value: String?): List<Int> {
        val listType =
            object : TypeToken<ArrayList<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListOfInteger(list: List<Int?>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromArrayListOfString(list: ArrayList<String?>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromArrayOfInt(list: IntArray?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromIntArrayString(value: String?): IntArray {
        val listType = object : TypeToken<IntArray?>() {}.type
        return Gson().fromJson(value, listType)
    }
}