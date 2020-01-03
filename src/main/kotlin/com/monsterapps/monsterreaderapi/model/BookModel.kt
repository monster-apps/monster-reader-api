package com.monsterapps.monsterreaderapi.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
data class BookModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="bookId")
    val pages : List<PageModel>,

    @OneToMany(cascade = [(CascadeType.ALL)])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="bookId")
    val selections : List<SelectionModel>
)