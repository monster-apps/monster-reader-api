package com.monsterapps.monsterreaderapi.dto

data class TranslationApiDTO(
    val code: Int,
    val lang: String,
    val text: Array<String>
)