package com.ua.jwtappdemo.security.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtTokenFilter(
        private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token = jwtTokenProvider.resolveToken(request as HttpServletRequest)
        val decodeJwt = jwtTokenProvider.verifyToken(token)

        if (decodeJwt != null) {

            val userId = decodeJwt.subject.toLong()
            val authentication = jwtTokenProvider.getAuthentication(userId)

            if (authentication != null)
                SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }
}