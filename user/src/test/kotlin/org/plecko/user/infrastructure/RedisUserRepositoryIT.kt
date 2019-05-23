package org.plecko.user.infrastructure

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.plecko.user.UserFixture.user
import org.plecko.user.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
@SpringBootTest
class RedisUserRepositoryIT {
    @Autowired
    lateinit var repository: UserRepository

    @Test
    fun `user saved and user retrieved are the same`() {
        repository.save(user())

        assertThat(repository.find(user().id), equalTo(user()))
    }
}