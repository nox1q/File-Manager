package kz.alabs.image_picker.data

import android.app.Application
import android.net.Uri
import android.os.Parcelable
import kz.alabs.image_picker.R
import kz.alabs.image_picker.ui.model.UIAddNewEventPhoto
import kz.alabs.image_picker.ui.model.UISelectedEventPhoto

interface UIAddNewEventPhotoData {

    fun getContentForPhotos(photoList: List<Uri>, showError: Boolean = false): List<Parcelable>
}

class UIAddNewEventPhotoDataDelegate(
    application: Application
) : UIAddNewEventPhotoData {

    private val res = application.resources
    private val dp8 = res.getDimension(R.dimen.dp_8)

    override fun getContentForPhotos(photoList: List<Uri>, showError: Boolean) =
        mutableListOf<Parcelable>().apply {
            photoList.forEach {
                add(UISelectedEventPhoto(it).apply {
                    top = dp8
                    left = dp8
                    right = dp8
                    bottom = dp8
                })
            }

            add(UIAddNewEventPhoto(showError).apply {
                top = dp8
                left = dp8
                right = dp8
                bottom = dp8
            })
        }
}