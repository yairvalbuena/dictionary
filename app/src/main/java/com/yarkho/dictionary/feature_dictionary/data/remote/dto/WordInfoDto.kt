package com.yarkho.dictionary.feature_dictionary.data.remote.dto

import com.yarkho.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.yarkho.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            license = license.toLicense(),
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word,
        )
    }
}