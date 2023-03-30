package com.chatgptlite.wanted.data.remote

import com.chatgptlite.wanted.models.TextCompletionsParam
import kotlinx.coroutines.flow.Flow

interface OpenAIRepository {
    fun textCompletionsWithStream(params: TextCompletionsParam): Flow<String>
}