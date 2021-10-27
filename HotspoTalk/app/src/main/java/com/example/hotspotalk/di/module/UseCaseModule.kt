package com.example.hotspotalk.di.module

import com.example.data.repository.AccountRepository
import com.example.domain.repository.ChatRepository
import com.example.data.repository.RoomsRepository
import com.example.domain.usecase.account.DeleteBanUseCase
import com.example.domain.usecase.account.GetIdUseCase
import com.example.domain.usecase.account.PostLoginUseCase
import com.example.domain.usecase.account.PostSignUpUseCase
import com.example.domain.usecase.chat.GetChatUseCase
import com.example.domain.usecase.rooms.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideDeleteBanUseCase(repository: AccountRepository) =
        DeleteBanUseCase(repository)

    @Singleton
    @Provides
    fun provideGetIdUseCase(repository: AccountRepository) =
        GetIdUseCase(repository)

    @Singleton
    @Provides
    fun providePostLoginUseCase(repository: AccountRepository) =
        PostLoginUseCase(repository)

    @Singleton
    @Provides
    fun providePostSignUpUseCase(repository: AccountRepository) =
        PostSignUpUseCase(repository)

    @Singleton
    @Provides
    fun provideGetChatUseCase(repository: ChatRepository) =
        GetChatUseCase(repository)

    @Singleton
    @Provides
    fun provideDeleteExitRoomUseCase(repository: RoomsRepository) =
        DeleteExitRoomUseCase(repository)

    @Singleton
    @Provides
    fun provideDeleteRemoveRoomUseCase(repository: RoomsRepository) =
        DeleteRemoveRoomUseCase(repository)

    @Singleton
    @Provides
    fun provideGetEnterRoomUseCase(repository: RoomsRepository) =
        GetEnterRoomUseCase(repository)

    @Singleton
    @Provides
    fun provideGetMemberUseCase(repository: RoomsRepository) =
        GetMemberUseCase(repository)

    @Singleton
    @Provides
    fun provideGetRoomsNotEnterableUseCase(repository: RoomsRepository) =
        GetRoomsNotEnterableUseCase(repository)

    @Singleton
    @Provides
    fun provideGetRoomsEnterableUseCase(repository: RoomsRepository): GetRoomsEnterableUseCase =
        GetRoomsEnterableUseCase(repository)

    @Singleton
    @Provides
    fun providePostCreateRoomUseCase(repository: RoomsRepository) =
        PostCreateRoomUseCase(repository)

    @Singleton
    @Provides
    fun providePutInheritRoomUseCase(repository: RoomsRepository) =
        PutInheritRoomUseCase(repository)

    @Singleton
    @Provides
    fun providePutModifyNicknameUseCase(repository: RoomsRepository) =
        PutModifyNicknameUseCase(repository)

    @Singleton
    @Provides
    fun providePutModifyRoomUseCase(repository: RoomsRepository) =
        PutModifyRoomUseCase(repository)
}