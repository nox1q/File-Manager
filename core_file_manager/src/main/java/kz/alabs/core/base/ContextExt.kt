package kz.alabs.core.base

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import java.io.File

fun Context.createFile(nameFile  : String, extFile : String) : File {
    val timeStamp = System.currentTimeMillis()
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "${timeStamp}_",
        extFile,
        storageDir
    )
}

fun TextView?.textColor(
    @ColorRes resId: Int
) {
    val context = this?.context ?: return
    this.setTextColor(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.getColor(context, resId)
        } else {
            resources.getColor(resId)
        }
    )
}
fun ViewPager.scrollListener(block: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            block(position)
        }
    })
}
fun Context.toPixel(dp: Float) =
    dp * resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT