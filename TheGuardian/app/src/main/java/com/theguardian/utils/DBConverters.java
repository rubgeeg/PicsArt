package com.theguardian.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.theguardian.models.Fields;
import com.theguardian.models.Result;

import java.util.ArrayList;

public class DBConverters {
    @TypeConverter
    public static ArrayList<Result> fromItemString(String value) {
        return new Gson().fromJson(value, new TypeToken<ArrayList<Result>>() {
        }.getType());
    }

    @TypeConverter
    public static String toItemList(ArrayList<Result> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static Fields fromFieldsString(String value) {
        return new Gson().fromJson(value, Fields.class);
    }

    @TypeConverter
    public static String toFields(Fields list) {
        return new Gson().toJson(list);
    }

}
