package org.plecko.user.infrastructure

import io.restassured.RestAssured
import io.restassured.RestAssured.`when`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.plecko.user.domain.UserRepository
import org.plecko.user.domain.user
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

    @BeforeEach
    fun init() {

        RestAssured.port = port
    }

    val user = user {
        id = "anId"
        name = "aName"
        email = "anEmail"
        sources {
            source {
                id = "sourceA"
            }
            source {
                id = "sourceB"
            }
        }
    }

    @Test
    fun x(){
        `when`().get("/users/user").then()
            .statusCode(204)
    }

    @Test
    fun testWhatever() {

        userRepository.save(user)

         `when`().get("/users/user").then()
             .statusCode(200)

    }
}