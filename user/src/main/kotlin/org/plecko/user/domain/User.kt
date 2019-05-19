package org.plecko.user.domain


data class User(var id: String, val name: String, val email: String, val sources: List<Source>)

data class Source(val id: String)

class SourceBuilder {
    var id: String = "unknown"
    fun build(): Source = Source(id)
}

class UserBuilder {
    var id: String = ""
    var name: String = ""
    var email: String = ""
    var sources: List<Source> = emptyList()

    fun build(): User = User(id, name, email, sources)

    fun sources(builder: SourcesBuilder.() -> Unit) {
        sources = SourcesBuilder().apply(builder).build()
    }
}

class SourcesBuilder {
    var sources: List<Source> = emptyList()

    fun source(builder: SourceBuilder.() -> Unit) {
        sources = sources + (SourceBuilder().apply(builder).build())
    }

    fun build(): List<Source> = sources
}

fun user(builder: UserBuilder.() -> Unit): User = UserBuilder().apply(builder).build()


