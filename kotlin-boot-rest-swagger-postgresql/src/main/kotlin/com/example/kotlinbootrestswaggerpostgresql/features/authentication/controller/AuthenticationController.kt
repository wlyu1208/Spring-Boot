package com.example.kotlinbootrestswaggerpostgresql.features.authentication.controller

import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.LoginRequestDto
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.LoginResponseDto
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.UserInfoDto
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.service.AuthenticationService
import com.example.kotlinbootrestswaggerpostgresql.jwt.JwtTokenManager
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@Api(value = "Authentication Controller")
class AuthenticationController(val authenticationService: AuthenticationService) {
    @Autowired
    private lateinit var jwtTokenManager: JwtTokenManager

    @PostMapping("/api/v1/login")
    fun login(@RequestBody body: LoginRequestDto): LoginResponseDto {
        return authenticationService.login(body)
    }

    @GetMapping("/api/v1/userInfo")
    fun getUserInfo(@RequestHeader("Authorization") authorizationToken: String?): UserInfoDto {
        if (authorizationToken != null){
            return authenticationService.getUserInfo(jwtTokenManager.getUserFromTokenWithBearer(authorizationToken))
        } else {
            throw HttpClientErrorException(HttpStatus.FORBIDDEN)
        }
    }
}