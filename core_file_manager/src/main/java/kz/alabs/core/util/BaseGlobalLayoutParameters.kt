package kz.alabs.core.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseGlobalLayoutParameter : Parcelable {
    var left: Float = 0f
    var top: Float = 0f
    var right: Float = 0f
    var bottom: Float = 0f

    var paddingLeft: Float? = null
    var paddingTop: Float? = null
    var paddingRight: Float? = null
    var paddingBottom: Float? = null
}