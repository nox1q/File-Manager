package kz.alabs.image_picker.ui.model

import android.net.Uri
import kotlinx.parcelize.Parcelize
import kz.alabs.core.util.BaseGlobalLayoutParameter

@Parcelize
data class UIGalleryPhotoModel(
    val uri: Uri,
    var isSelected: Boolean = false
) : BaseGlobalLayoutParameter()