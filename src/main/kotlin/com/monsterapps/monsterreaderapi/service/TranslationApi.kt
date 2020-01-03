package com.monsterapps.monsterreaderapi.service

import com.monsterapps.monsterreaderapi.dto.TranslationApiDTO
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct


//  https://translate.yandex.com/developers/keys
@Service
class TranslationApi(private val builder: RestTemplateBuilder) {
    private lateinit var restTemplate: RestTemplate

    @PostConstruct
    fun setup(){
        restTemplate = builder.build()
    }

    fun translate(selection:String): String? {
        val fooResourceUrl = "https://translate.yandex.net/api/v1.5/tr.json"
        val key = "&key=trnsl.1.1.20191126T062901Z.33f255bdfd1ca96e.16b1ebf1a965e64a7cc439c46960fdd927be238c"
        val lang = "&lang=nl-pt"
        val text = "&text=$selection"
        val route = "/translate?$key$text$lang"
        val response =  restTemplate.getForEntity(
                "$fooResourceUrl$route",
                TranslationApiDTO::class.java
        )

        return response.body?.text?.get(0) ?: ""
    }
}