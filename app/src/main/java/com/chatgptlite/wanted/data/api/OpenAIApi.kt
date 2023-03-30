package com.chatgptlite.wanted.data.api

import com.chatgptlite.wanted.constants.textCompletionsTurboEndpoint
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface OpenAIApi {
    @POST(textCompletionsTurboEndpoint)
    @Streaming
    fun textCompletionsWithStream(@Body body: JsonObject): Call<ResponseBody>
}