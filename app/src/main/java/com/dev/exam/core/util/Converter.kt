package com.dev.exam.core.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dev.exam.core.util.parser.JsonParser
import com.google.gson.reflect.TypeToken

/*
@ProvidedTypeConverter // to allow constructor arguments for class
class Converter(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromXJson(json: String): List<X> {
        return jsonParser.fromJson<ArrayList<X>>(json,
            object : TypeToken<ArrayList<X>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toXJson(x: String): String {
        return jsonParser.toJson<ArrayList<X>>(x,
            object : TypeToken<ArrayList<X>>() {}.type
        ) ?: "[]"
    }
}*/