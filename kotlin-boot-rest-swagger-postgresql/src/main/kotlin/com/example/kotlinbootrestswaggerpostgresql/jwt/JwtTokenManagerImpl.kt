package com.example.kotlinbootrestswaggerpostgresql.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
@Configuration
class JwtTokenManagerImpl : JwtTokenManager {
    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.issuer}")
    private val issuer: String? = null

    @Value("\${jwt.validity_ms}")
    private val validityInMs: Long? = null

    override fun generateToken(username: String): String {
        val key = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
        return Jwts.builder()
            .setSubject(username)
            .setIssuer(issuer)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis().plus(validityInMs!!)))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    override fun tokenValidate(token: String): Boolean {
        return getUserFromToken(token) != null && !isExpired(token)
    }

    override fun isExpired(token: String): Boolean {
        val key = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.expiration.before(Date(System.currentTimeMillis()))
//        return Jwts.parser().setSigningKey(secret)
//            .parseClaimsJws(token).body.expiration.before(Date(System.currentTimeMillis()))
    }

    override fun getUserFromToken(token: String): String? {
        val key = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    override fun getUserFromTokenWithBearer(token: String): String? {
        val key = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.substring(7)).body.subject
    }
}