package kz.noxiq.filemanager.image_preview

import android.app.Application
import androidx.lifecycle.LiveData
import kz.noxiq.core.base.BaseViewModel
import kz.noxiq.core.base.SingleLiveEvent
import kz.noxiq.filemanager.image_picker.ui.model.EventItemImageItemDVO
import kz.noxiq.filemanager.utils.Images

class ImagePreviewViewModel(
    application: Application
) : BaseViewModel(application) {

    private val _initImages = SingleLiveEvent<List<EventItemImageItemDVO>>()
    val initImages: LiveData<List<EventItemImageItemDVO>>
        get() = _initImages

    var localEventFullData: List<EventItemImageItemDVO>? = null

    fun onViewCreated() {
        localEventFullData = Images.images()
        _initImages.value = localEventFullData
    }

}