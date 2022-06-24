package com.example.barmenu.tools

import com.example.barmenu.BuildConfig

class GenerateImgLink {

    fun generateLink(name: String): String {
        val correctTypeFormat = name.replace(" ", "%20")
        val url = "${BuildConfig.BASE_INGREDIENTS_URL}${correctTypeFormat}.png"

        return url
    }

}