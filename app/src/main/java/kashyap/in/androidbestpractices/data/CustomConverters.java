package kashyap.in.androidbestpractices.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomConverters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static List<Integer> fromStringInteger(String value) {
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListOfInteger(List<Integer> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static String fromArrayListOfString(ArrayList<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static String fromArrayOfInt(int[] list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static int[] fromIntArrayString(String value) {
        Type listType = new TypeToken<int[]>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
}
