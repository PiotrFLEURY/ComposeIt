package fr.piotrfleury.composeit.presentation.usecases

import fr.piotrfleury.composeit.domain.entities.User
import fr.piotrfleury.composeit.domain.repositories.UserRepository

class FetchNewUsers(private val userRepository: UserRepository) {
    operator fun invoke(): List<User> = userRepository.getUsers(10)
}