package org.plecko.user.infrastructure

import org.hamcrest.CoreMatchers.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.plecko.user.domain.UserRepository
import org.plecko.user.domain.user
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import redis.embedded.RedisServer

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
@SpringBootTest
class RedisUserRepositoryIT {
    @Autowired
    lateinit var repository: UserRepository

    @Test
    fun `user saved and user retrieved are the same`() {

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

        repository.save(user)

        val foundUser = repository.find(user.id)

        assertThat(foundUser, equalTo(user))
    }
}