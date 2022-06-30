package com.example.web_application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
