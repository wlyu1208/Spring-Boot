package com.example.kotlinbootrestswaggerpostgresql.jwt

interface JwtTokenManager {
    fun generateToken(username: String): String
    fun tokenValidate(token: String): Boolean
    fun getUserFromToken(token: String): String?
    fun getUserFromTokenWithBearer(token: String): String?
    fun isExpired(token: String): Boolean
}