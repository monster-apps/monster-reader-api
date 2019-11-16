package com.monsterapps.monsterreaderapi.controller

import com.monsterapps.monsterreaderapi.dto.BookDTO
import com.monsterapps.monsterreaderapi.service.BookService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

typealias BookList = ResponseEntity<List<BookDTO>>

@RestController
@RequestMapping("/api/v1/books")
class Books(private val bookService: BookService){
    @GetMapping
    fun getBooks() = ResponseEntity.ok(bookService.getAll())

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postBook(@RequestBody booksDTO:BookDTO) {
        bookService.insertBook(booksDTO)
    }
}