package com.monsterapps.monsterreaderapi.model

import java.sql.Blob
import javax.persistence.*

@Entity
data class SelectionModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val index: Long,
    val selection : String,
    val translation : String,
    val page : Long
)