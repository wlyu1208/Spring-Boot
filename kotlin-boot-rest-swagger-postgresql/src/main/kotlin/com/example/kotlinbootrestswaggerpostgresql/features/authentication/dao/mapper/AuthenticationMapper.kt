package com.example.kotlinbootrestswaggerpostgresql.features.authentication.dao.mapper

import com.example.kotlinbootrestswaggerpostgresql.features.authentication.dao.entity.User
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.fromUserDaoToUserInfo() = UserInfoDto (
    email = this[User.email],
    name = this[User.name],
    surname = this[User.surname],
    password = this[User.password]
)