package com.chatgptlite.wanted.di

import com.chatgptlite.wanted.data.remote.ConversationRepository
import com.chatgptlite.wanted.data.remote.ConversationRepositoryImpl
import com.chatgptlite.wanted.data.remote.MessageRepository
import com.chatgptlite.wanted.data.remote.MessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun conversationRepository(
        repo: ConversationRepositoryImpl
    ): ConversationRepository

    @Binds
    abstract fun messageRepository(
        repo: MessageRepositoryImpl
    ): MessageRepository
}
