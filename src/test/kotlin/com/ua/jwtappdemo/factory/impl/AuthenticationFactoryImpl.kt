package com.ua.jwtappdemo.factory.impl

import com.ua.jwtappdemo.factory.AuthenticationFactory
import org.springframework.stereotype.Component

@Component
class AuthenticationFactoryImpl : AuthenticationFactory {
    override fun createSuccessLoginStatement(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["username"] = "test"
        map["password"] = "test"
        return map
    }

    override fun createFailedLoginStatement(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["username"] = "test"
        map["password"] = "wrong_password"
        return map
    }
}