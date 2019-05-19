package org.plecko.user.domain

import org.plecko.user.infrastructure.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
@RequestMapping("/user")
@RestController
class UserAPI(private val userService: UserService) {
    val logger = LoggerFactory.getLogger(UserAPI::class.java)

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<Set<User>> {
        return response(userService.findUsers())
    }

    @GetMapping("/{id}",produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@RequestParam id: String): ResponseEntity<User> =
        response(userService.findUser(id))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody user: User): ResponseEntity<Void> {
        userService.saveUser(user)
        return ResponseEntity(HttpStatus.OK)
    }

    private fun response(user: User?): ResponseEntity<User> =
        user?.let { ok(it) } ?: ResponseEntity.noContent().build()

    private fun <T:Any>response(users: Set<T>): ResponseEntity<Set<T>> =
       if (users.isEmpty())  ResponseEntity.noContent().build() else ok(users)
}