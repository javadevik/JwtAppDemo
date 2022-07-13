package com.ua.jwtappdemo.factory.impl

import com.ua.jwtappdemo.factory.UserFactory
import org.springframework.stereotype.Component

@Component
class UserFactoryImpl : UserFactory {
    override fun create(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["username"] = "user"
        map["firstName"] = "Test user"
        map["lastName"] = "Test user"
        map["email"] = "user@gmail.com"
        map["password"] = "root"
        return map
    }

    override fun createUpdated(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["username"] = "user"
        map["firstName"] = "Test updated"
        map["lastName"] = "Test updated"
        map["email"] = "updated@gmail.com"
        map["password"] = "root"
        return map
    }
}