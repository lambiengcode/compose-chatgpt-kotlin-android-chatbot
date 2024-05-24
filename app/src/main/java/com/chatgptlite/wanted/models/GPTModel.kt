package com.chatgptlite.wanted.models

enum class GPTModel(val model: String, val maxTokens: Int, val isChatCompletion: Boolean = false) {
    gpt4("gpt-4", 8192, isChatCompletion = true),
    gpt4o("gpt-4o", 4096, isChatCompletion = true),
    gpt4o20240513("gpt-4o-2024-05-13", 4096, isChatCompletion = true),
    gpt4turbo("gpt-4-turbo-2024-04-09", 4096, isChatCompletion = true),
    gpt4turbopreview("gpt-4-turbo-preview", 4096, isChatCompletion = true),
    gpt41106preview("gpt-4-1106-preview", 4096, isChatCompletion = true),
    gpt35Turbo("gpt-3.5-turbo", 4096),

    // Deprecated
//    gpt4v0613("gpt-4-0613", 8192, isChatCompletion = true),
//    gpt4p32k("gpt-4-32k", 32768, isChatCompletion = true),
//    gpt4p32kv0613("gpt-4-32k-0613", 32768, isChatCompletion = true),
//    davinci("text-davinci-003", 4096),
//    curie("text-curie-001", 2048),
//    babbage("text-babbage-001", 2048),
//    ada("text-ada-001", 2048)
}