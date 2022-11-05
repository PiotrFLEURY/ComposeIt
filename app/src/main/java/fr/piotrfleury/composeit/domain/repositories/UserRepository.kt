package fr.piotrfleury.composeit.domain.repositories

import fr.piotrfleury.composeit.domain.entities.User

interface UserRepository {

    fun getUsers(results: Int): List<User>

}