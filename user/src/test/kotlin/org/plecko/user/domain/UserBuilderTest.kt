package org.plecko.user.domain

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test


class UserBuilderTest {
    @Test
    fun `creates an user`() {
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

        assertThat(user, equalTo(User("anId","aName", "anEmail", listOf(Source("sourceA"), Source("sourceB")))))
    }
}