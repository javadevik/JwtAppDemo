package com.ua.jwtappdemo.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    companion object {
        @JvmStatic
        private val postgreSQLContainer = PostgreSQLContainer("postgres:13-alpine")
            .withExposedPorts(7369)
            .withDatabaseName("fortestingdb")
            .withUsername("postgres")
            .withPassword("root")
            .apply { start() }
    }

    @Bean
    fun postgreSQLContainer(): PostgreSQLContainer<*> {
        return postgreSQLContainer
    }

    @Bean
    fun testDataSource(postgreSQLContainer: PostgreSQLContainer<*>): DataSource {
        val config = HikariConfig().apply {
            driverClassName = postgreSQLContainer.driverClassName
            jdbcUrl = postgreSQLContainer.jdbcUrl
            username = postgreSQLContainer.username
            password = postgreSQLContainer.password
        }
        return HikariDataSource(config)
    }
}