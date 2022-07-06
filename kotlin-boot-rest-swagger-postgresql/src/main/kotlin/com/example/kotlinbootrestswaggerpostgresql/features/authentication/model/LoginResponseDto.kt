package com.example.kotlinbootrestswaggerpostgresql.features.authentication.model

import io.swagger.annotations.ApiModel

@ApiModel
data class LoginResponseDto(val token: String)
