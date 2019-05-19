package org.plecko.user

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties


@SpringBootApplication
@EnableAutoConfiguration
@ConfigurationProperties

open class App

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger(App::class.java)
    logger.info("User is starting")
    SpringApplication.run(App::class.java, *args)
}