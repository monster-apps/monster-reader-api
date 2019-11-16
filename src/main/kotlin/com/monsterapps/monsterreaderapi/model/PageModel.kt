package com.monsterapps.monsterreaderapi.model

import java.sql.Blob
import javax.persistence.*

@Entity
data class PageModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val page: Long,
    @Column(length = 2000)
    val text : String
)