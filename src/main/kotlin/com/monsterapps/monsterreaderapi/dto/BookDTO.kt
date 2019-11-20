package com.monsterapps.monsterreaderapi.dto

import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import org.jetbrains.annotations.NotNull
import java.util.regex.Pattern

data class BookDTO(
    @NotNull
    val title:String,
    val text: String
){
    fun convertToModel():BookModel {
        fun String.bookToPages(length: Int): List<String> = replace("(?:\\s*)(.{1,$length})(?:\\s+|\\s*$)".toRegex(), "$1\n").split("\n".toRegex()).dropLastWhile { it.isEmpty() }

        fun List<String>.toPageModel() = withIndex()
                .map { (index, text) -> PageModel(page=index.toLong(), text = text)}
        // tet
        val pageList = text.bookToPages(380)
        return BookModel(title=title, pages= pageList.toPageModel())
    }
}

data class BookIdResponse (val id: Long)
data class BookReadResponse (
    val text: String?,
    val page: Long,
    val totalPages: Long
)