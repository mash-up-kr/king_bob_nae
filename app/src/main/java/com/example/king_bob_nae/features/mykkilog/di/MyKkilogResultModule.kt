package com.example.king_bob_nae.features.mykkilog.di

import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.data.deletekkilog.RemoteDeleteKkilogImpl
import com.example.king_bob_nae.features.mykkilog.data.getkkilog.RemoteGetKkilogImpl
import com.example.king_bob_nae.features.mykkilog.data.patchkkilog.RemotePatchKkilogImpl
import com.example.king_bob_nae.features.mykkilog.data.postkkiloglike.RemotePostKkilogLikeImpl
import com.example.king_bob_nae.features.mykkilog.data.postkkilogscrap.RemotePostKkilogScrapImpl
import com.example.king_bob_nae.features.mykkilog.data.postkkilogunlike.RemotePostKkilogUnLikeImpl
import com.example.king_bob_nae.features.mykkilog.data.postkkilogunscrap.RemotePostKkilogUnScrapImpl
import com.example.king_bob_nae.features.mykkilog.domain.deletekkilog.RemoteDeleteKkilog
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.RemoteGetKkilog
import com.example.king_bob_nae.features.mykkilog.domain.patchkkilog.RemotePatchKkilog
import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.RemotePostKkilogLike
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap.RemotePostKkilogScrap
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunlike.RemotePostKkilogUnLike
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunscrap.RemotePostKkilogUnScrap
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MyKkilogResultModule {
    companion object {
        @Singleton
        @Provides
        fun providesSimpleKkilogApi(retrofit: Retrofit): SimpleKkilogApi = retrofit.create()
    }

    @Singleton
    @Binds
    abstract fun bindsRemoteDeleteKkilog(remoteDeleteKkilogImpl: RemoteDeleteKkilogImpl): RemoteDeleteKkilog

    @Singleton
    @Binds
    abstract fun bindsRemoteGetKkilog(remoteGetKkilog: RemoteGetKkilogImpl): RemoteGetKkilog

    @Singleton
    @Binds
    abstract fun bindsPatchKkilog(remotePatchKkilogImpl: RemotePatchKkilogImpl): RemotePatchKkilog

    @Singleton
    @Binds
    abstract fun bindsPostKkilogLike(remotePostKkilogLike: RemotePostKkilogLikeImpl): RemotePostKkilogLike

    @Singleton
    @Binds
    abstract fun bindPostKkilogUnLike(remotePostKkilogUnLike: RemotePostKkilogUnLikeImpl): RemotePostKkilogUnLike

    @Singleton
    @Binds
    abstract fun bindPostKkilogScrap(remotePostKkilogScrap: RemotePostKkilogScrapImpl): RemotePostKkilogScrap

    @Singleton
    @Binds
    abstract fun bindPostKkilogUnScrap(remotePostKkilogUnScrap: RemotePostKkilogUnScrapImpl): RemotePostKkilogUnScrap
}
