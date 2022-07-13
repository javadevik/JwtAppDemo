package com.ua.jwtappdemo.factory

interface AuthenticationFactory {
    fun createSuccessLoginStatement(): Map<String, String>
    fun createFailedLoginStatement(): Map<String, String>
}