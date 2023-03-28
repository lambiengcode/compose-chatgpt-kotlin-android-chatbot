package com.chatgptlite.wanted.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri

class UrlLauncher {

    fun openUrl(context: Context, url: String) {
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        context.startActivity(urlIntent)
    }
}