package fr.piotrfleury.composeit.data.sources

import fr.piotrfleury.composeit.data.models.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api")
    fun getUsers(@Query("results") results: Int): Call<RandomUserResponse>
}