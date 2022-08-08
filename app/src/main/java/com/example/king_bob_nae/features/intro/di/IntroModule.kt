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
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IntroModule {
    @Binds
    @Singleton
    abstract fun bindSignInRepository(
        repository: SignInRepositoryImpl
    ): SignInRepository

    @Binds
    @Singleton
    abstract fun bindSignUpRepository(
        repository: SignUpRepositoryImpl
    ): SignUpRepository

    companion object {
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
    }
}
