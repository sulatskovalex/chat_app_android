package com.github.sulatskovalex.chat_app

import android.app.Application
import android.content.Context
import com.github.sulatskovalex.data.AccessTokenProvider
import com.github.sulatskovalex.data.ChatAppServiceGrpc
import com.github.sulatskovalex.data.PreferencesService
import com.github.sulatskovalex.data.repositories.authorization.AuthRepositoryImpl
import com.github.sulatskovalex.data.repositories.users.UsersRepositoryImpl
import com.github.sulatskovalex.domain.authorization.AuthInteractor
import com.github.sulatskovalex.domain.authorization.AuthRepository
import com.github.sulatskovalex.domain.users.UsersRepository
import io.grpc.android.AndroidChannelBuilder
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class ChatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    commonModule(this@ChatApp),
                    authModule(),
                    mainModule()
                )
            )
        }
    }

    private fun commonModule(context: Context): Module = module {
        val preferencesService = PreferencesService(context)
        single { preferencesService }
        single<AccessTokenProvider> { preferencesService }
        single {
            ChatAppServiceGrpc.newStub(
                AndroidChannelBuilder.forAddress("localhost", 8080)
                    .context(context)
                    .usePlaintext()
                    .build()
            )
        }
    }

    private fun authModule(): Module = module {
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
        single { AuthInteractor(get()) }
    }

    private fun mainModule(): Module = module {
        single<UsersRepository> { UsersRepositoryImpl(get(), get(), get()) }
    }
}