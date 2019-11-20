package com.monsterapps.monsterreaderapi.model

import javax.persistence.*

@Entity
data class BookModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name="bookId")
    val pages : List<PageModel>
)