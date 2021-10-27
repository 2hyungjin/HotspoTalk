package com.example.hotspotalk.di.module

import com.example.data.datasource.AccountDataSource
import com.example.data.datasource.ChatDataSource
import com.example.data.datasource.RoomDataSource
import com.example.data.repositoryImpl.AccountRepositoryImpl
import com.example.data.repositoryImpl.ChatRepositoryImpl
import com.example.data.repositoryImpl.RoomsRepositoryImpl
import com.example.data.repository.AccountRepository
import com.example.domain.repository.ChatRepository
import com.example.data.repository.RoomsRepository
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
    fun provideAccountRepository(dataSource: AccountDataSource): AccountRepository =
        AccountRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun provideChatRepository(datasource: ChatDataSource): ChatRepository =
        ChatRepositoryImpl(datasource)

    @Singleton
    @Provides
    fun provideRoomsRepository(datasource: RoomDataSource): RoomsRepository =
        RoomsRepositoryImpl(datasource)
}