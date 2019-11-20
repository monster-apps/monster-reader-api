package com.monsterapps.monsterreaderapi.exception

import java.util.UUID

data class MonsterExceptionResponse(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val message: String
)