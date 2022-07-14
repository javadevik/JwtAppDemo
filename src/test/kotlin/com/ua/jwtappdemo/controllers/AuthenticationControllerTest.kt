package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.factory.AuthenticationFactory
import com.ua.jwtappdemo.factory.UserFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthenticationControllerTest(
    @Autowired
        private val webTestClient: WebTestClient,
    @Autowired
        private val userFactory: UserFactory,
    @Autowired
        private val authFactory: AuthenticationFactory
) {

    @Test
    fun registrationTest() {
        val response = webTestClient
                .post()
                .uri("/api/auth/registration")
                .body(BodyInserters.fromValue(userFactory.create()))
                .exchange()

        response.expectStatus().isOk
    }

    @Test
    fun loginSuccessTest() {
        val map = mutableMapOf<String, String>()
        map["username"] = "test"
        map["firstName"] = "Test user"
        map["lastName"] = "Test user"
        map["email"] = "test@gmail.com"
        map["password"] = "test"

        webTestClient
            .post()
            .uri("/api/auth/registration")
            .body(BodyInserters.fromValue(map))
            .exchange()

        val response = webTestClient
            .post()
            .uri("/api/auth/login")
            .body(BodyInserters.fromValue(authFactory.createSuccessLoginStatement()))
            .exchange()

        println(response)

        response.expectStatus().isOk
    }

    @Test
    fun loginFailedTest() {
        val response = webTestClient
            .post()
            .uri("/api/auth/registration")
            .body(BodyInserters.fromValue(authFactory.createFailedLoginStatement()))
            .exchange()
        response.expectStatus().is4xxClientError
    }
}