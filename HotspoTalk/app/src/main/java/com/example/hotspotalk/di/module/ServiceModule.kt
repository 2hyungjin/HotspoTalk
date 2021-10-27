package com.example.hotspotalk.di.module

import com.example.hotspotalk.data.service.AccountService
import com.example.hotspotalk.data.service.MessageService
import com.example.hotspotalk.data.service.RoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)

    @Singleton
    @Provides
    fun provideChatService(retrofit: Retrofit): MessageService =
        retrofit.create(MessageService::class.java)

    @Singleton
    @Provides
    fun provideRoomServiceService(retrofit: Retrofit): RoomService =
        retrofit.create(RoomService::class.java)

}