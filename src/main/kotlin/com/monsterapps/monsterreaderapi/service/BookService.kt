package com.monsterapps.monsterreaderapi.service

import com.monsterapps.monsterreaderapi.dto.BookDTO
import com.monsterapps.monsterreaderapi.dto.BookSelectDTO
import com.monsterapps.monsterreaderapi.dto.ReadDTO
import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import com.monsterapps.monsterreaderapi.model.SelectionModel
import com.monsterapps.monsterreaderapi.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(private val bookRepository: BookRepository, private val translationApi:TranslationApi) {
    fun getAll():List<BookModel> = bookRepository.findAll()
    fun getById(id:Long) = bookRepository.findById(id)
    fun getByPage(id:Long, page: Long): BookModel {
        val bookSelections = bookRepository.getSelectionsByPage(id, page)
        val bookPages = bookRepository.findByPage(id, page) ?: throw IllegalStateException()
        return BookModel(id=bookPages.id, title=bookPages.title, pages = bookPages.pages, selections =  bookSelections?.selections ?: emptyList())
    }
    fun getTotalBookPages(id:Long):Long = bookRepository.getAllPagesByBook(id)
    fun getBookSelections(id: Long) = bookRepository.findById(id).get().selections?.map { it.selection }

    @Transactional
    fun insertBook(booksDTO:BookDTO): Long {
        val bookModel = booksDTO.convertToModel()
        return bookRepository.save(bookModel).id
    }

    @Transactional
    fun insertBookSelection(id: Long, bookSelectDTO:BookSelectDTO): SelectionModel {
        val translationWord = translationApi.translate(bookSelectDTO.selection).orEmpty()
        val bookSelectionModel = bookSelectDTO.convertToModel(translationWord )
        val bookModel = getById(id).orElseThrow { throw  IllegalStateException("Book not found") }
        val bookSelections = bookModel.selections.plus(bookSelectionModel)
        val book = BookModel(id=bookModel.id, title=bookModel.title, pages = bookModel.pages, selections =  bookSelections )
        bookRepository.save(book)
        return bookSelectionModel
    }

}
