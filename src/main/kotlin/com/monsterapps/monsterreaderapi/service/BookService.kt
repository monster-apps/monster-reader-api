package com.monsterapps.monsterreaderapi.service

import com.monsterapps.monsterreaderapi.dto.BookDTO
import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import com.monsterapps.monsterreaderapi.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(private val bookRepository: BookRepository) {
    fun getAll():List<BookModel> = bookRepository.findAll()

    @Transactional
    fun insertBook(booksDTO:BookDTO){
        val bookModel = booksDTO.convertToModel()
        bookRepository.save(bookModel)
    }
}

