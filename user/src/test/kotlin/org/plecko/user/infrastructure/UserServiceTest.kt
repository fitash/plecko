package org.plecko.user.infrastructure

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.plecko.user.UserFixture
import org.plecko.user.domain.FindUserUseCase
import org.plecko.user.domain.FindUsersUseCase
import org.plecko.user.domain.SaveUserUseCase

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @MockK
    private lateinit var findUserUseCase: FindUserUseCase
    @MockK
    private lateinit var saveUserUseCase: SaveUserUseCase
    @MockK
    private lateinit var findUsersUseCase: FindUsersUseCase

    @InjectMockKs
    private lateinit var service: UserService

    @Test
    fun `finds user`() {
        val anId = "anId"

        every { findUserUseCase.execute(anId) } returns UserFixture.user()

        assertThat(service.findUser(anId), `is`(equalTo(UserFixture.user())))
    }

    @Test
    fun `finds users`() {
        every { findUsersUseCase.execute() } returns setOf(UserFixture.user())

        assertThat(service.findUsers(), contains(UserFixture.user()))
    }

    @Test
    fun `saves user`() {
        every { saveUserUseCase.execute(UserFixture.user()) } just Runs

        service.saveUser(UserFixture.user())

        verify { saveUserUseCase.execute(UserFixture.user()) }
    }
}