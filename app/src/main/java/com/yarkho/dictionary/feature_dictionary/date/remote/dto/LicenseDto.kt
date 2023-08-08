package com.yarkho.dictionary.feature_dictionary.date.remote.dto

import com.yarkho.dictionary.feature_dictionary.domain.model.License

data class LicenseDto(
    val name: String,
    val url: String
){
    fun toLicense():License{
        return License(
            name = name,
            url = url,
        )
    }
}