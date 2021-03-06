package com.example.hotspotalk.di.module

import com.example.hotspotalk.data.service.RoomService
import com.example.hotspotalk.data.repository.AccountRepository
import com.example.hotspotalk.data.repository.RoomsRepository
import com.example.hotspotalk.data.repositoryImpl.AccountRepositoryImpl
import com.example.hotspotalk.data.repositoryImpl.ChattingRepositoryImpl
import com.example.hotspotalk.data.repositoryImpl.RoomsRepositoryImpl
import com.example.hotspotalk.data.service.AccountService
import com.example.hotspotalk.data.service.ChattingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccountRepository(service: AccountService): AccountRepository =
        AccountRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideChatRepository(service: ChattingService): com.example.hotspotalk.data.repository.ChattingRepository =
        ChattingRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideRoomsRepository(service: RoomService): RoomsRepository =
        RoomsRepositoryImpl(service)
}