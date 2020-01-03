package com.monsterapps.monsterreaderapi.controller

import com.monsterapps.monsterreaderapi.dto.*
import com.monsterapps.monsterreaderapi.model.SelectionModel
import com.monsterapps.monsterreaderapi.service.BookService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class Books(private val bookService: BookService){
    @GetMapping
    fun getBooks() = ResponseEntity.ok(bookService.getAll())

    @GetMapping("/{id}")
    fun getBooksById(@PathVariable id:Long) = ResponseEntity.ok(bookService.getById(id))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postBook(@RequestBody booksDTO:BookDTO) = ResponseEntity.ok(BookIdResponse(bookService.insertBook(booksDTO)) )

    @GetMapping("/{id}/read")
    fun getBooksByRead(@PathVariable id:Long, @RequestParam page: Long = 0): ResponseEntity<BookReadResponse> {
        val bookModel = bookService.getByPage(id, page)
        val totalPages =  bookService.getTotalBookPages(id)
        return ResponseEntity.ok(BookReadResponse(totalPages=totalPages, text= bookModel?.pages?.get(0)?.text, page = page, selections = bookModel?.selections ?: emptyList()) )
    }

    @GetMapping( "/{id}/selections",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBookSelections(@PathVariable id:Long): List<String>? {
        return bookService.getBookSelections(id)
    }

    @PostMapping( "/{id}/selections",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postBookSelections(@PathVariable id:Long, @RequestBody bookSelectDTO:BookSelectDTO): ResponseEntity<SelectionModel> {
        return ResponseEntity.ok(bookService.insertBookSelection(id, bookSelectDTO))
    }
}

