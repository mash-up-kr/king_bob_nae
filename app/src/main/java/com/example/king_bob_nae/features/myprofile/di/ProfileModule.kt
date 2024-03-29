package com.example.king_bob_nae.features.myprofile.di

import com.example.king_bob_nae.features.myprofile.data.ProfileApi
import com.example.king_bob_nae.features.myprofile.data.userfollow.follow.RemotePostUserFriendFollowImpl
import com.example.king_bob_nae.features.myprofile.data.userfollow.getuserfollow.RemoteGetUserFollowImpl
import com.example.king_bob_nae.features.myprofile.data.userfollow.unfollow.RemotePostUserFriendUnFollowImpl
import com.example.king_bob_nae.features.myprofile.data.userprofile.RemoteGetUserProfileImpl
import com.example.king_bob_nae.features.myprofile.domain.userfollow.RemoteGetUserFollow
import com.example.king_bob_nae.features.myprofile.domain.userfriend.follow.RemotePostUserFriendFollow
import com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow.RemotePostUserFriendUnFollow
import com.example.king_bob_nae.features.myprofile.domain.userprofile.RemoteGetUserProfile
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
abstract class ProfileModule {

    companion object {
        @Singleton
        @Provides
        fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create()
    }

    @Binds
    @Singleton
    abstract fun bindRemoteGetUserProfile(remoteGetUserProfile: RemoteGetUserProfileImpl): RemoteGetUserProfile

    @Binds
    @Singleton
    abstract fun bindRemoteGetUserFollow(remoteGetUserFollow: RemoteGetUserFollowImpl): RemoteGetUserFollow

    @Binds
    @Singleton
    abstract fun bindRemotePostUserFriendFollow(remotePostUserFriendFollow: RemotePostUserFriendFollowImpl): RemotePostUserFriendFollow

    @Binds
    @Singleton
    abstract fun bindRemotePostUserFriendUnFollow(remotePostUserFriendUnFollow: RemotePostUserFriendUnFollowImpl): RemotePostUserFriendUnFollow
}
