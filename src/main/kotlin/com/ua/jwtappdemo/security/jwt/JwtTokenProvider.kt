package com.ua.jwtappdemo.security.jwt

import com.ua.jwtappdemo.entities.UserEntity
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    @Value("\${jwt.security.secret}")
    private var secret: String,
    private val clock: Clock,
    private val userDetailsService: UserDetailsService
) {
    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(user: UserEntity): String {
        val username = user.username
        val password = user.password

        val claims = Jwts.claims().setSubject(username)
        claims["password"] = password

        val nowTime = clock.millis()
        val validity = nowTime + 3600000

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(nowTime))
            .setExpiration(Date(validity))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.isNotEmpty() && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }

    fun validateToken(token: String?): Boolean {

        if (token == null)
            return false

        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)

        if (claims.body.expiration.before(Date())) {
            return false
        }

        return true
    }
}