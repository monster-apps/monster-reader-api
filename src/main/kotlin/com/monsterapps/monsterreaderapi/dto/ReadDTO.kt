package com.monsterapps.monsterreaderapi.dto

import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.PageModel
import com.monsterapps.monsterreaderapi.model.SelectionModel
import org.jetbrains.annotations.NotNull
import java.util.regex.Pattern
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.OneToMany

data class ReadDTO(
    val page : PageModel,
    val totalPages: Long,
    val selection: SelectionModel
)
