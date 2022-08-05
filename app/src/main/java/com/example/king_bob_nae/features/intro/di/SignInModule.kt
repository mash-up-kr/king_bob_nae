package com.example.king_bob_nae.features.intro.di

import com.example.king_bob_nae.features.intro.data.repository.SignInRepository
import com.example.king_bob_nae.features.intro.data.repository.impl.SignInRepositoryImpl
import com.example.king_bob_nae.features.intro.data.service.SignInService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class SignInModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSignInRepository(
        repository: SignInRepositoryImpl
    ): SignInRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideService(): SignInService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SignInService::class.java)

        private const val BASE_URL = "https://kki-log-api.herokuapp.com/api/"
    }

}