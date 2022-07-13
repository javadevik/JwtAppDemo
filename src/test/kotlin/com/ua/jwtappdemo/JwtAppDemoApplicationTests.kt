package com.ua.jwtappdemo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class JwtAppDemoApplicationTests(
	@Autowired
	private val dataSourceContainer: PostgreSQLContainer<*>
) {

	@Test
	@DisplayName("test container is up and running")
	fun testContainer() {
		Assertions.assertTrue(dataSourceContainer.isRunning)
	}

	@Test
	fun contextLoads() {
	}

}
