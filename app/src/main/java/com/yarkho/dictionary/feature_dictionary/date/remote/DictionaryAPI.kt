package com.yarkho.dictionary.feature_dictionary.date.remote

import com.yarkho.dictionary.feature_dictionary.date.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>
}