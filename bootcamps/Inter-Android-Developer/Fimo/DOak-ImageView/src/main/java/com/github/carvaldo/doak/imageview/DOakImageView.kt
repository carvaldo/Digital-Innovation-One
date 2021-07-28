package com.github.carvaldo.doak.imageview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.net.URL

private val TAG = DOakImageView::class.java.canonicalName

/**
 * TODO: document your custom view class.
 */
class DOakImageView : androidx.appcompat.widget.AppCompatImageView {

    private val client = OkHttpClient.Builder()
        .cache(
            Cache(
                directory = File(context.cacheDir, "http_cache"),
                // $0.05 worth of phone storage in 2020
                maxSize = 50L * 1024L * 1024L // 50 MiB
            )
        )
        .build()

    @Suppress("MemberVisibilityCanBePrivate")
    var uri: Uri? = null
        set(value) {
            field = value
            invalidateImage()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.DOakImageView, defStyle, 0
        )
        if (a.hasValue(R.styleable.DOakImageView_url)) {
            uri = a.getString(R.styleable.DOakImageView_url)?.toUri()
        }
        a.recycle()
        invalidateImage()
    }

    private fun invalidateImage() {
        when (uri != null) {
            true -> {
                MainScope().launch(Dispatchers.IO) {
                    val request = Request.Builder()
                        .url(URL(uri.toString()))
                        .build()
                    val response = client.newCall(request).execute()
                    if (response.isSuccessful) {
                        val stream = response.body?.byteStream()
                        val drawable = BitmapDrawable.createFromStream(stream, BuildConfig.LIBRARY_PACKAGE_NAME)
                        withContext(Dispatchers.Main) {
                            this@DOakImageView.setImageDrawable(drawable)
                        }
                    } else {
                        //TODO fail
                        Log.e(TAG, "Response FAIL", )
                    }
                }
            }
            false -> setImageDrawable(null)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}