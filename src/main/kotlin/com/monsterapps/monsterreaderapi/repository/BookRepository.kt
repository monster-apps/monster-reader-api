package com.monsterapps.monsterreaderapi.repository

import com.monsterapps.monsterreaderapi.model.BookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookModel, Long>