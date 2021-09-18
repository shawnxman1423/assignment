package io.liba.assignment.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkClient(@ApplicationContext context: Context): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

}
