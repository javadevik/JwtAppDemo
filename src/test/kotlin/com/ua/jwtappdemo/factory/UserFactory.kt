package com.ua.jwtappdemo.factory

interface UserFactory {
    fun create(): Map<String, String>
    fun createUpdated(): Map<String, String>
}