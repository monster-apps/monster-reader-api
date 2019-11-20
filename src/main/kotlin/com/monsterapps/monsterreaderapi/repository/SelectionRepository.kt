package com.monsterapps.monsterreaderapi.repository

import com.monsterapps.monsterreaderapi.model.BookModel
import com.monsterapps.monsterreaderapi.model.SelectionModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SelectionRepository: JpaRepository<SelectionModel, Long>