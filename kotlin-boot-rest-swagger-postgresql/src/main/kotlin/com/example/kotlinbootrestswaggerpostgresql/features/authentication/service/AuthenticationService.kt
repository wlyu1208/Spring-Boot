package com.example.kotlinbootrestswaggerpostgresql.features.authentication.service

import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.LoginRequestDto
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.LoginResponseDto
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.UserInfoDto
import org.springframework.security.core.userdetails.UserDetailsService

interface AuthenticationService : UserDetailsService {
    fun login(request: LoginRequestDto): LoginResponseDto
    fun getUserInfo(email: String?): UserInfoDto
    fun createUser(userInfoDto: UserInfoDto)
}