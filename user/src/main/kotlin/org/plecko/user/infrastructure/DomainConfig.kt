package org.plecko.user.infrastructure


import org.plecko.user.domain.FindUserUseCase;
import org.plecko.user.domain.FindUsersUseCase
import org.plecko.user.domain.SaveUserUseCase
import org.plecko.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
open class DomainConfig {
    @Bean
    open fun findUserUseCase(userRepository: UserRepository): FindUserUseCase = FindUserUseCase(userRepository)

    @Bean
    open fun findUserUsersCase(userRepository: UserRepository): FindUsersUseCase = FindUsersUseCase(userRepository)

    @Bean
    open fun saveUserUseCase(userRepository: UserRepository): SaveUserUseCase = SaveUserUseCase(userRepository)
}
