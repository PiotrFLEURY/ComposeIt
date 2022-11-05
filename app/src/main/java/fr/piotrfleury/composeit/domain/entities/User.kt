package fr.piotrfleury.composeit.domain.entities

data class User(
    val lastName: String,
    val firstName: String,
    val email: String,
    val picture: String,
    val phone: String
)