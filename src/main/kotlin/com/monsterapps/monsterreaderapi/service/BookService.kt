package com.monsterapps.monsterreaderapi.service

import com.monsterapps.monsterreaderapi.dto.BookDTO
import com.monsterapps.monsterreaderapi.dto.ReadDTO
import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import com.monsterapps.monsterreaderapi.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(private val bookRepository: BookRepository) {
    fun getAll():List<BookModel> = bookRepository.findAll()
    fun getById(id:Long): Optional<BookModel> = bookRepository.findById(id)
    fun getByPage(id:Long, page: Long) = bookRepository.findByPage(id, page)
    fun getTotalBookPages(id:Long):Long = bookRepository.getAllPagesByBook(id)

    @Transactional
    fun insertBook(booksDTO:BookDTO): Long {
        val bookModel = booksDTO.convertToModel()
        return bookRepository.save(bookModel).id
    }
}