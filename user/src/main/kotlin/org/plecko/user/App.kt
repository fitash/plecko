package org.plecko.user

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile


@SpringBootApplication
@EnableAutoConfiguration
@ConfigurationProperties

open class App
fun main(args: Array<String>) {
    println("Starting")
    SpringApplication.run(App::class.java, *args)
    println("Ending")
}