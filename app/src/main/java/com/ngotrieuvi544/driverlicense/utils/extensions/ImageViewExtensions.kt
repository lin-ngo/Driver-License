package com.ngotrieuvi544.driverlicense.utils.extensions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ngotrieuvi544.driverlicense.R

fun ImageView.loadGlideImageFromUrl(context: Context?, urlString: String) {

    context?.let {
        val circularProgressDrawable = CircularProgressDrawable(it).apply {
            strokeWidth = 5f
            centerRadius = 30f
        }

        circularProgressDrawable.start()

        Glide.with(it)
            .load(urlString)
            .apply(RequestOptions().apply {
                placeholder(circularProgressDrawable)
                error(R.drawable.image_no_thumbnail_available)
                fitCenter()
            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
