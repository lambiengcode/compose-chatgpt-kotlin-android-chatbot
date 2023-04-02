package com.chatgptlite.wanted.models

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class TextCompletionsParam(
    @SerializedName("prompt")
    val promptText: String = "",
    @SerializedName("temperature")
    val temperature: Double = 0.9,
    @SerializedName("top_p")
    val topP: Double = 1.0,
    @SerializedName("n")
    val n: Int = 1,
    @SerializedName("stream")
    var stream: Boolean = true,
    @SerializedName("maxTokens")
    val maxTokens: Int = 2048,
    @SerializedName("model")
    val model: GPTModel = GPTModel.gpt35Turbo,
    @SerializedName("messages")
    val messagesTurbo: List<MessageTurbo> = emptyList(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextCompletionsParam

        if (promptText != other.promptText) return false
        if (temperature != other.temperature) return false
        if (topP != other.topP) return false
        if (n != other.n) return false
        if (stream != other.stream) return false
        if (maxTokens != other.maxTokens) return false
        if (model != other.model) return false
        if (messagesTurbo != other.messagesTurbo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = promptText.hashCode()
        result = 31 * result + temperature.hashCode()
        result = 31 * result + topP.hashCode()
        result = 31 * result + n
        result = 31 * result + stream.hashCode()
        result = 31 * result + maxTokens
        result = 31 * result + model.hashCode()
        result = 31 * result + messagesTurbo.hashCode()
        return result
    }

    val isTurbo: Boolean
        get() = model == GPTModel.gpt35Turbo
}

fun TextCompletionsParam.toJson(): JsonObject {
    val json = JsonObject()
    json.addProperty("temperature", temperature)
    json.addProperty("stream", stream)
    json.addProperty("model", model.model)

    if (model == GPTModel.gpt35Turbo) {
        val jsonArray = JsonArray()
        for (message in messagesTurbo) jsonArray.add(message.toJson())

        json.add("messages", jsonArray)
    } else {
        json.addProperty("prompt", promptText)
    }

    return json
}