package com.theguardian.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.io.File

@BindingAdapter("loadImageUrl", "loadImageFile", "placeHolder", requireAll = false)
fun loadImage(
    imageView: ImageView,
    url: String? = "",
    imageFile: File? = File(""),
    placeHolder: Int = 0
) {
    Glide
        .with(imageView.context)
        .load(
            if (url != null && url.isNotEmpty())
                url
            else
                imageFile
        )
        .placeholder(placeHolder)
        .into(imageView)
}