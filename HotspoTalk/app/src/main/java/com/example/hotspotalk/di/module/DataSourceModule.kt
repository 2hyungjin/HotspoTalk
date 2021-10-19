package com.example.hotspotalk.di.module

import com.example.data.datasource.AccountDataSource
import com.example.data.datasource.ChatDataSource
import com.example.data.datasource.RoomDataSource
import com.example.data.service.AccountService
import com.example.data.service.ChatService
import com.example.data.service.RoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAccountDataSource(service: AccountService): AccountDataSource =
        AccountDataSource(service)

    @Singleton
    @Provides
    fun provideChatDataSource(service: ChatService): ChatDataSource =
        ChatDataSource(service)

    @Singleton
    @Provides
    fun provideRoomDataSource(service: RoomService): RoomDataSource =
        RoomDataSource(service)
}