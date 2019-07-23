package com.razan.weatherkotlin.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.razan.weatherkotlin.utils.FLAG_URL
import com.squareup.picasso.Picasso

object BindingAdapters{
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String) {
        Picasso.get().load("$FLAG_URL$url.png").fit().into(view)
    }
}