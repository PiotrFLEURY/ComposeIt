package fr.piotrfleury.composeit

import android.app.Application
import fr.piotrfleury.composeit.data.repositories.UserRepositoryImpl
import fr.piotrfleury.composeit.data.sources.RandomUserApi
import fr.piotrfleury.composeit.domain.repositories.UserRepository
import fr.piotrfleury.composeit.presentation.usecases.FetchNewUsers
import fr.piotrfleury.composeit.presentation.viewmodels.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComposeItApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val composeItMainModule = module {

            single { buildUserApi() } bind RandomUserApi::class
            single { UserRepositoryImpl(get()) } bind UserRepository::class
            single { FetchNewUsers(get()) }
            single { UserListViewModel(get()) }
        }

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@ComposeItApplication)
            modules(composeItMainModule)
        }
    }

    private fun buildUserApi(): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }
}