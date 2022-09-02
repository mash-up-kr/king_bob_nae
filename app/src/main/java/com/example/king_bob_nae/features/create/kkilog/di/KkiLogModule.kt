package com.example.king_bob_nae.features.create.kkilog.di

import com.example.king_bob_nae.features.create.kkilog.data.repository.KkiLogRepository
import com.example.king_bob_nae.features.create.kkilog.data.repository.KkiLogRepositoryImpl
import com.example.king_bob_nae.features.create.kkilog.data.service.KkiLogService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KkiLogModule {

    @Binds
    @Singleton
    abstract fun bindKkiLogRepository(
        repository: KkiLogRepositoryImpl
    ): KkiLogRepository

    companion object {
        @Provides
        @Singleton
        fun provideUpLoadKkiLogService(retrofit: Retrofit): KkiLogService {
            return retrofit.create(KkiLogService::class.java)
        }
    }
}