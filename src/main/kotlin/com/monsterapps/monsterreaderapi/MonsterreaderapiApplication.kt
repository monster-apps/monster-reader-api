package com.monsterapps.monsterreaderapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@SpringBootApplication
class MonsterreaderapiApplication {
	@Bean
	fun cors() = FilterRegistrationBean(CorsFilter(UrlBasedCorsConfigurationSource().apply {
		registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
	}))
}

fun main(args: Array<String>) {
	runApplication<MonsterreaderapiApplication>(*args)
}
