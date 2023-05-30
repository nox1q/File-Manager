package kz.noxiq.filemanager.image_picker

import android.app.Application
import android.net.Uri
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.noxiq.core.base.BaseViewModel
import kz.noxiq.core.base.SingleLiveEvent
import kz.noxiq.filemanager.image_picker.data.UIAddNewEventPhotoData
import kz.noxiq.filemanager.image_picker.data.UIAddNewEventPhotoDataDelegate
import kz.noxiq.filemanager.image_picker.ui.model.CreateNewEventParam
import kz.noxiq.filemanager.image_picker.ui.model.UIAddPhotoAction
import kz.noxiq.filemanager.image_picker.ui.model.UISelectedEventPhoto
import java.io.File

class ImagePickerViewModel(application: Application) : BaseViewModel(application),
    UIAddNewEventPhotoData by UIAddNewEventPhotoDataDelegate(application) {

    private val _contents = MutableLiveData<List<Parcelable>>()
    val contents: LiveData<List<Parcelable>>
        get() = _contents

    private val _showCamera = SingleLiveEvent<Unit>()
    val showCamera: LiveData<Unit>
        get() = _showCamera

    private val _showGallery = SingleLiveEvent<Unit>()
    val showGallery: LiveData<Unit>
        get() = _showGallery

    private val _selectedPhotosFromGallery = MutableLiveData<List<Uri>>()
    val selectedPhotosFromGallery: LiveData<List<Uri>>
        get() = _selectedPhotosFromGallery

    private var photoList = mutableListOf<Uri>()

    fun onViewCreated() {
        setupContents()
    }

    fun onRemovePhoto(item: UISelectedEventPhoto) {
        photoList.remove(item.photo)

        setupContents()
    }

    private fun setupContents() {
        _contents.value = getContentForPhotos(photoList)
    }

    fun onActionClicked(action: String) {
        when (action) {
            UIAddPhotoAction.TAKE_PHOTO.name -> {
                _showCamera.value = Unit
            }
            UIAddPhotoAction.SELECT_FROM_GALLERY.name -> {
                _showGallery.value = Unit
            }
        }
    }

    fun onTakePhoto(photo: String) {
        photoList.add(Uri.fromFile(File(photo)))
        setupContents()
    }

    fun onPhotoSelected(photos: List<Uri>) {
        photoList.clear()
        photoList.addAll(photos)
        _selectedPhotosFromGallery.value = photoList

        setupContents()
    }
}