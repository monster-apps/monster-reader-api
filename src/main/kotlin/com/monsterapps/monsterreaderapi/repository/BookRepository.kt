package com.monsterapps.monsterreaderapi.repository

import com.monsterapps.monsterreaderapi.model.BookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface BookRepository: JpaRepository<BookModel, Long> {
    @Query(value = """
        select b from BookModel b 
        left join fetch b.pages p 
        where b.id=?1 AND p.page = ?2
    """)
    fun findByPage(bookId:Long, page: Long): BookModel?
    @Query(value = "select size(book.pages) from BookModel book where book.id=?1")
    fun getAllPagesByBook(id: Long): Long

    @Query(value = """
        select b from BookModel b 
        left join fetch b.selections s 
        where b.id=?1 AND s.page = ?2 
    """)
    fun getSelectionsByPage(bookId:Long, page: Long): BookModel?
}
