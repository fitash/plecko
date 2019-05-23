package org.plecko.user

import org.plecko.user.domain.User
import org.plecko.user.domain.user

object UserFixture {

    inline fun user(): User = user {
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

}
