package com.yarkho.dictionary.feature_dictionary.date.remote.dto

data class PhoneticDto(
    val audio: String,
    val license: LicenseDto,
    val sourceUrl: String,
    val text: String
)