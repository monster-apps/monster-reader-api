package com.monsterapps.monsterreaderapi.repository

import com.monsterapps.monsterreaderapi.model.BookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface BookRepository: JpaRepository<BookModel, Long> {
    @Query(value = """
        select book from BookModel book 
        inner join fetch book.pages page 
        inner join fetch book.selections s 
        where book.id=?1  
        and page.page=?2
        and (s.page is null 
        OR s.page=?2)
    """)
    fun findByPage(bookId:Long, page: Long): BookModel?
    @Query(value = "select size(book.pages) from BookModel book where book.id=?1")
    fun getAllPagesByBook(id: Long): Long
}
