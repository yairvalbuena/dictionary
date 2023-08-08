package com.yarkho.dictionary.feature_dictionary.date.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yarkho.dictionary.feature_dictionary.domain.model.License
import com.yarkho.dictionary.feature_dictionary.domain.model.Meaning
import com.yarkho.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val sourceUrls: List<String>,
    val meanings: List<Meaning>,
    val license: License,
    @PrimaryKey val id: Int? = null,
){
    fun toWordInfo(): WordInfo{
        return WordInfo(
            meanings = meanings,
            word = word,
            sourceUrls = sourceUrls,
            phonetic = phonetic,
            license = license
        )
    }
}
