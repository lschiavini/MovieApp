package com.lucas.schiavini.movieapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Ignore
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonObject
import com.lucas.schiavini.movieapp.R


fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    val imageBaseUrl = "https://image.tmdb.org/t/p/original/"
    val finalUrl = imageBaseUrl + url
    view.loadImage(finalUrl, getProgressDrawable(view.context))
}

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}