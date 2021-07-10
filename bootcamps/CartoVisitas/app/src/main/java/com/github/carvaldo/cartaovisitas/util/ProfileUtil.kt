package com.github.carvaldo.cartaovisitas.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.*
import kotlin.coroutines.CoroutineContext

class ProfileUtil {
    companion object {
        fun gerarThumb(inputStream: InputStream, output: File,
                       width: Int, height: Int, autoClose: Boolean = true): Bitmap {
            val outputStream = FileOutputStream(output)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val scaled = Bitmap.createScaledBitmap(bitmap, width, height, true)
            scaled.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            if (autoClose) {
                inputStream.close()
                outputStream.close()
            }
            bitmap.recycle()
            return scaled
        }
    }
}