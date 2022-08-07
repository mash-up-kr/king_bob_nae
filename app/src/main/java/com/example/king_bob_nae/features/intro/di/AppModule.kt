package com.example.king_bob_nae.features.intro.di

import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import com.example.king_bob_nae.features.intro.data.repository.impl.SignInRepositoryImpl
import com.example.king_bob_nae.features.intro.data.repository.impl.SignUpRepositoryImpl
import com.example.king_bob_nae.features.intro.data.service.SignInService
import com.example.king_bob_nae.features.intro.data.service.SignUpService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSignInRepository(
        repository: SignInRepositoryImpl
    ): SignInRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSignUpRepository(
        repository: SignUpRepositoryImpl
    ): SignUpRepository

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    @Singleton
    fun provideSignInService(retrofit: Retrofit): SignInService {
        return retrofit.create(SignInService::class.java)
    }


    companion object {
        private const val BASE_URL = "http://kki-log-api.turastory.com/"
    }
}