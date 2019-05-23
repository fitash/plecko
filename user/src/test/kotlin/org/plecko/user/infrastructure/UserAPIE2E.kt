package org.plecko.user.infrastructure

import io.restassured.RestAssured
import io.restassured.RestAssured.`when`
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.plecko.user.UserFixture
import org.plecko.user.UserFixture.user
import org.plecko.user.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAPIE2E {
    @Autowired
    lateinit var userRepository: UserRepository

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var redisConnection: RedisConnection

    @BeforeEach
    fun init() {
        redisConnection.sync().flushall()
        RestAssured.port = port
    }

    @Test
    fun `retrieves users when there are none`() {
        `when`().get("/users/user")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
    }

    @Test
    fun `retrieves users`() {
        userRepository.save(user())
        `when`().get("/users/user")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", hasItem(user().id))
                .body("name", hasItem(user().name))
                .body("email", hasItem(user().email))
                .body("sources.id", equalTo(listOf(listOf("sourceA", "sourceB"))))

    }
}