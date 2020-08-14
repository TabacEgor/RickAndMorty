package com.example.rickandmorty.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object GlideHelper {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(
        view: ImageView,
        imageUrl: String?
    ) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions().transform(RoundedCornersTransformation(view.context, 30, 0, RoundedCornersTransformation.CornerType.LEFT)))
                .into(view)
        }
    }

    fun loadImageSingleCharacter(context: Context, imageUrl: String?, iv: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().transform(RoundedCorners(30)))
            .into(iv)
    }
}