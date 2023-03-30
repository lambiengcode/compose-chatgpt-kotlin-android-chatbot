package com.chatgptlite.wanted.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class MessageTurbo(
    @SerializedName("content")
    val content: String = "",
    @SerializedName("role")
    val role: TurboRole = TurboRole.user,
)

fun MessageTurbo.toJson() : JsonObject {
    val json = JsonObject()
    json.addProperty("content", content)
    json.addProperty("role", role.value)

    return json
}
