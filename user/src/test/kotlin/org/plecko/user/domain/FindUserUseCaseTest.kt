package org.plecko.user.domain

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class FindUserUseCaseTest {
    @MockK
    private lateinit var repository: UserRepository

    @InjectMockKs
    private lateinit var useCase: FindUserUseCase

    private val expecterUser = user {
        id = "aname"
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
    fun `the repository is called as expected`() {
        val anyId = "anyId"
        every { repository.find(anyId) } returns expecterUser

        assertThat(useCase.execute(anyId), equalTo(expecterUser))
        verify { repository.find(anyId) }
    }
}