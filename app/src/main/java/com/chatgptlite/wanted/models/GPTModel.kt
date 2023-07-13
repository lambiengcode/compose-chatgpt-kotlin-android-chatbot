package com.chatgptlite.wanted.models

enum class GPTModel(val model: String, val maxTokens: Int, val isChatCompletion: Boolean = false) {
    gpt4("gpt-4", 8192, isChatCompletion = true),
    gpt4v0613("gpt-4-0613", 8192, isChatCompletion = true),
    gpt4p32k("gpt-4-32k", 32768, isChatCompletion = true),
    gpt4p32kv0613("gpt-4-32k-0613", 32768, isChatCompletion = true),
    gpt35Turbo("gpt-3.5-turbo", 4096),
    davinci("text-davinci-003", 4096),
    curie("text-curie-001", 2048),
    babbage("text-babbage-001", 2048),
    ada("text-ada-001", 2048)
}