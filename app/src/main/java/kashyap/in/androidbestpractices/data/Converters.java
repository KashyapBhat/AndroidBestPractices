package kashyap.in.androidbestpractices.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kashyap.in.androidbestpractices.data.dao.GithubRepoDetailsDao;
import kashyap.in.androidbestpractices.data.models.GithubRepoDetails;
import kashyap.in.androidbestpractices.data.models.OwnerDetails;

public class Converters {
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

    @TypeConverter
    public static String fromListOfGithubRepoDetails(List<GithubRepoDetails> allowedValues) {
        Gson gson = new Gson();
        return gson.toJson(allowedValues);
    }

    @TypeConverter
    public static List<GithubRepoDetails> toListOfGithubRepoDetails(String allowedValues) {
        Type type = new TypeToken<ArrayList<GithubRepoDetails>>() {
        }.getType();
        return new Gson().fromJson(allowedValues, type);
    }

    @TypeConverter
    public static String fromGithubRepoDetails(GithubRepoDetails allowedValues) {
        Gson gson = new Gson();
        return gson.toJson(allowedValues);
    }

    @TypeConverter
    public static GithubRepoDetails toGithubRepoDetails(String allowedValues) {
        Type type = new TypeToken<GithubRepoDetails>() {
        }.getType();
        return new Gson().fromJson(allowedValues, type);
    }

    @TypeConverter
    public static String fromOwnerDetails(OwnerDetails allowedValues) {
        Gson gson = new Gson();
        return gson.toJson(allowedValues);
    }

    @TypeConverter
    public static OwnerDetails toOwnerDetails(String allowedValues) {
        Type type = new TypeToken<OwnerDetails>() {
        }.getType();
        return new Gson().fromJson(allowedValues, type);
    }
}
