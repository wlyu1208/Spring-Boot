package com.example.kotlinbootrestswaggerpostgresql.database

import org.jetbrains.exposed.sql.Database
import java.util.Properties

fun Database.Companion.connectToUserDB() {
    val properties = Properties()
    val inputStream = javaClass.classLoader.getResourceAsStream("application.properties")
    properties.load(inputStream)
    connect(
        url =  properties.getProperty("spring.datasource.url"),
        driver = properties.getProperty("spring.datasource.driverClassName"),
        user = properties.getProperty("spring.datasource.username"),
        password = properties.getProperty("spring.datasource.password")
    )
}