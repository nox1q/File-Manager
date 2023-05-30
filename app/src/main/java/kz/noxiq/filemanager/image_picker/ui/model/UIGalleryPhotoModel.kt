package kz.noxiq.filemanager.image_picker.ui.model

import android.net.Uri
import kotlinx.parcelize.Parcelize
import kz.noxiq.core.util.BaseGlobalLayoutParameter

@Parcelize
data class UIGalleryPhotoModel(
    val uri: Uri,
    var isSelected: Boolean = false
) : BaseGlobalLayoutParameter()