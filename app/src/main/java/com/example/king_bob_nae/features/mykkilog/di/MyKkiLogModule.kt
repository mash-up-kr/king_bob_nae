package com.example.king_bob_nae.features.mykkilog.di

import com.example.king_bob_nae.features.mykkilog.data.repository.MyKkiLogRepository
import com.example.king_bob_nae.features.mykkilog.data.repository.MyKkiLogRepositoryImpl
import com.example.king_bob_nae.features.mykkilog.data.service.MyKkiLogService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MyKkiLogModule {

    @Binds
    @Singleton
    abstract fun bindMyKkiLogRepository(
        repository: MyKkiLogRepositoryImpl
    ): MyKkiLogRepository


    companion object {
        @Provides
        @Singleton
        fun provideMyKkiLogService(retrofit: Retrofit): MyKkiLogService =
            retrofit.create(MyKkiLogService::class.java)
    }
}