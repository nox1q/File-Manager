package kz.alabs.image_preview.ui.image_preview

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.alabs.core.base.BaseViewModel
import kz.alabs.core.base.SingleLiveEvent

class ImagePreviewViewModel(
    application: Application
) : BaseViewModel(application) {

    private val _initImages = MutableLiveData<List<String>>()
    val initImages: LiveData<List<String>>
        get() = _initImages

    private val _currentImagePosition = MutableLiveData<Int>()
    val currentImagePosition: LiveData<Int>
        get() = _currentImagePosition

    var listImages: List<String>? = null

    fun setInitImages(list: ArrayList<String>) {
        listImages = list
        _initImages.value = listImages

    }

    fun onPositionChange(position: Int) {
        _currentImagePosition.value = position
    }

}