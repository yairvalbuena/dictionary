package com.yarkho.dictionary.feature_dictionary.date.remote.dto

import com.yarkho.dictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
){
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            antonyms = antonyms,
            synonyms = synonyms,
        )
    }
}