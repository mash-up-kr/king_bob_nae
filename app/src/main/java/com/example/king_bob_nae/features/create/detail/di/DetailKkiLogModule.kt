package com.example.king_bob_nae.features.create.detail.di

import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import com.example.king_bob_nae.features.create.detail.data.repository.impl.DetailKkiLogRepositoryImpl
import com.example.king_bob_nae.features.create.detail.data.service.DetailKkiLogService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailKkiLogModule {

    @Binds
    @Singleton
    abstract fun bindDetailKkiLogRepository(
        repository: DetailKkiLogRepositoryImpl
    ): DetailKkiLogRepository

    companion object {
        @Provides
        @Singleton
        fun provideDetailKkiLogService(retrofit: Retrofit): DetailKkiLogService {
            return retrofit.create(DetailKkiLogService::class.java)
        }
    }
}
