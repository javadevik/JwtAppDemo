package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.factory.AuthenticationFactory
import com.ua.jwtappdemo.factory.UserFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql("/init_db_before_test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql("/restore_db_after_test.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
        val response = webTestClient
            .post()
            .body(BodyInserters.fromValue(authFactory.createSuccessLoginStatement()))
            .exchange()
        response.expectStatus().isOk
    }

    @Test
    fun loginFailedTest() {
        val response = webTestClient
            .post()
            .body(BodyInserters.fromValue(authFactory.createFailedLoginStatement()))
            .exchange()
        response.expectStatus().is4xxClientError
    }
}