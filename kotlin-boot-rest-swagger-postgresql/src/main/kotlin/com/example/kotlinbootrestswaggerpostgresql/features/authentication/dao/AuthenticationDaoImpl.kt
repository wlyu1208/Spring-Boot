package com.example.kotlinbootrestswaggerpostgresql.features.authentication.dao

import com.example.kotlinbootrestswaggerpostgresql.database.connectToUserDB
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.dao.entity.User
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.dao.mapper.fromUserDaoToUserInfo
import com.example.kotlinbootrestswaggerpostgresql.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class AuthenticationDaoImpl() : AuthenticationDao {
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun login(username: String): UserDetails {
        Database.connectToUserDB()

        val userInfo = transaction {
            addLogger(StdOutSqlLogger)
            return@transaction User.select { User.email eq username }.single().fromUserDaoToUserInfo()
        }
        return org.springframework.security.core.userdetails.User(userInfo.email, userInfo.password, ArrayList())
    }

    override fun getUserInfo(email: String?): UserInfoDto {
        Database.connectToUserDB()

        val userInfo = transaction {
            addLogger(StdOutSqlLogger)
            return@transaction User.select { User.email eq email!! }.single().fromUserDaoToUserInfo()
        }
        return userInfo
    }

    override fun createUser(userInfoDto: UserInfoDto) {
        Database.connectToUserDB()

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(User)
            User.insert {
                it[name] = userInfoDto.name
                it[surname] = userInfoDto.surname
                it[email] = userInfoDto.email
                it[password] = bCryptPasswordEncoder.encode(userInfoDto.password)
            }
        }
    }
}