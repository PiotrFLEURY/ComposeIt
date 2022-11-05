package fr.piotrfleury.composeit.data.repositories

import fr.piotrfleury.composeit.data.sources.RandomUserApi
import fr.piotrfleury.composeit.domain.entities.User
import fr.piotrfleury.composeit.domain.repositories.UserRepository

class UserRepositoryImpl(private val randomUserApi: RandomUserApi) : UserRepository {
    override fun getUsers(results: Int): List<User> {
        randomUserApi.getUsers(results)
            .execute()
            .body()?.let { randomUserResponse ->
                return randomUserResponse.results.map { randomUser ->
                    User(
                        lastName = randomUser.name.last,
                        firstName = randomUser.name.first,
                        email = randomUser.email,
                        picture = randomUser.picture.large,
                        phone = randomUser.phone
                    )
                }
            }
        throw Exception("Unable to fetch users")
    }
}