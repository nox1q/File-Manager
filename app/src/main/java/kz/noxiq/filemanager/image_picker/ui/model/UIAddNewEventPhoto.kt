package kz.noxiq.filemanager.image_picker.ui.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.noxiq.core.util.BaseGlobalLayoutParameter

/**
 * [AddNewEventPhotoViewBinder]
 */
@Parcelize
data class UIAddNewEventPhoto(
    val displayError: Boolean = false
) : BaseGlobalLayoutParameter()

/**
 * [SelectedEventPhotoViewBinder]
 */
@Parcelize
data class UISelectedEventPhoto(
    val photo: Uri
) : BaseGlobalLayoutParameter()

enum class UIAddPhotoAction {
    TAKE_PHOTO,
    SELECT_FROM_GALLERY
}

@kotlinx.android.parcel.Parcelize
data class EventItemImageItemDVO(
    val uri: String,
    val isMain: Boolean
): Parcelable