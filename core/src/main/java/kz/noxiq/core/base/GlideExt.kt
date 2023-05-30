package kz.noxiq.core.base

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kz.noxiq.core.util.CoreConstant
import kz.noxiq.core.util.CoreFileConstants

fun ImageView.loadImage(url: Any) {
    val isSvg = getFileExtension(url.toString()) == CoreFileConstants.SVG_FILE
    val request = if (isSvg) {
        Glide.with(this.context).`as`(Drawable::class.java)
            .transition(DrawableTransitionOptions.withCrossFade())
            .load(Uri.parse(url.toString()))
    } else {
        Glide.with(this.context)
            .load(url)
    }
    request.centerCrop().into(this)
}

fun getFileExtension(uri: String?): String {
    if (uri == null) {
        return CoreConstant.EMPTY
    }
    val dot = uri.lastIndexOf(".")
    return if (dot >= 0) {
        uri.substring(dot)
    } else {
        CoreConstant.EMPTY
    }
}