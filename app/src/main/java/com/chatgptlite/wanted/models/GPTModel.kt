package com.chatgptlite.wanted.models

enum class GPTModel(val model: String, val maxTokens: Int) {
    gpt35Turbo("gpt-3.5-turbo", 4000),
    davinci("text-davinci-003", 4000),
    curie("text-curie-001", 2048),
    babbage("text-babbage-001", 2048),
    ada("text-ada-001", 2048)
}