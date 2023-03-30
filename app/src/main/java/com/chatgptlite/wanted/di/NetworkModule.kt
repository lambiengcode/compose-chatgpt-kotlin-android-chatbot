package com.chatgptlite.wanted.di

import com.chatgptlite.wanted.constants.baseUrlOpenAI
import com.chatgptlite.wanted.constants.openAIApiKey
import com.chatgptlite.wanted.data.api.OpenAIApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(
            Interceptor { chain ->
                var request: Request? = null
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer $openAIApiKey")
                request = requestBuilder.build()
                chain.proceed(request)
            })
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(baseUrlOpenAI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenAIService(retrofit: Retrofit): OpenAIApi =
        retrofit.create(OpenAIApi::class.java)
}