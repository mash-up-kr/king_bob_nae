package com.example.king_bob_nae.features.intro.di

import com.example.king_bob_nae.features.intro.data.repository.SignUpRepository
import com.example.king_bob_nae.features.intro.data.repository.impl.SignUpRepositoryImpl
import com.example.king_bob_nae.features.intro.data.service.SignUpService
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
abstract class SignupModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSignUpRepository(
        repository: SignUpRepositoryImpl
    ): SignUpRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideService(): SignUpService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SignUpService::class.java)

        private const val BASE_URL = "https://kki-log-api.herokuapp.com/api/"
    }
}
