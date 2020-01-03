package com.monsterapps.monsterreaderapi.dto

import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import com.monsterapps.monsterreaderapi.model.SelectionModel
import org.jetbrains.annotations.NotNull

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
        return BookModel(title=title, pages= pageList.toPageModel(), selections = emptyList() )
    }
}

data class BookIdResponse (val id: Long)
data class BookReadResponse (
    val text: String?,
    val page: Long,
    val totalPages: Long,
    val selections : List<SelectionModel>?
)

data class BookSelectDTO (
 val index: Long,
 val page: Long,
 val selection: String
){
    fun convertToModel(translation:String):SelectionModel = SelectionModel(index=index, selection=selection.toLowerCase(), page=page, translation=translation)
}