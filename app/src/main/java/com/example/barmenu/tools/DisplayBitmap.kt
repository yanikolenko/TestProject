package com.example.barmenu.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.util.concurrent.Executors

class DisplayBitmap {

    fun displayPhoto(url: String, imageView: ImageView){

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        executor.execute {

            try {
                val stream = java.net.URL(url).openStream()
                image = BitmapFactory.decodeStream(stream)

                handler.post {
                    imageView.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}