package com.example.king_bob_nae.features.home.di

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.data.freindlist.RemoteGetFriendListImpl
import com.example.king_bob_nae.features.home.data.freindsstatus.RemoteGetFriendsStatusImpl
import com.example.king_bob_nae.features.home.data.friendshome.RemoteGetAllKkilogImpl
import com.example.king_bob_nae.features.home.data.levelup.HomeLevelUpImpl
import com.example.king_bob_nae.features.home.data.userstate.RemoteGetHomeUserStatusImpl
import com.example.king_bob_nae.features.home.domain.freindlist.RemoteGetFriendList
import com.example.king_bob_nae.features.home.domain.friendsStatus.RemoteGetFriendsUserStatus
import com.example.king_bob_nae.features.home.domain.friendshome.RemoteGetAllkkilog
import com.example.king_bob_nae.features.home.domain.levelup.HomeLevelUp
import com.example.king_bob_nae.features.home.domain.userstate.RemoteGetHomeUserStatus
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
abstract class HomeModule {
    companion object {
        @Provides
        @Singleton
        fun providesHomeApi(retrofit: Retrofit): HomeApi = retrofit.create()
    }

    @Binds
    @Singleton
    abstract fun bindRemoteGetHomeStatus(remoteGetHomeStatus: RemoteGetHomeUserStatusImpl): RemoteGetHomeUserStatus

    @Binds
    @Singleton
    abstract fun bindRemoteGetFriendList(remoteGetFriendList: RemoteGetFriendListImpl): RemoteGetFriendList

    @Binds
    @Singleton
    abstract fun bindRemoteGetFriendsStatus(remoteGetFriendsUserStatus: RemoteGetFriendsStatusImpl): RemoteGetFriendsUserStatus

    @Binds
    @Singleton
    abstract fun bindLevelUp(homeLevelUp: HomeLevelUpImpl): HomeLevelUp

    @Binds
    @Singleton
    abstract fun bindAllKkilog(remoteGetAllKkilogImpl: RemoteGetAllKkilogImpl): RemoteGetAllkkilog
}
