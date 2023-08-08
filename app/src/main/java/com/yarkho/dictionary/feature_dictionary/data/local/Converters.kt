package com.yarkho.dictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import com.google.gson.reflect.TypeToken
import com.yarkho.dictionary.feature_dictionary.data.util.JsonParser
import com.yarkho.dictionary.feature_dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converters (
    private val jsonParser: JsonParser
){
    fun fromMeaningsJson(json: String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    fun toMeaningsJson(meanings: List<Meaning>):String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}