package kz.alabs.image_picker.ui.bottom_sheet

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import kz.alabs.core.adapter.MultiTypeAdapter
import kz.alabs.core.base.BaseBottomSheetFragment
import java.io.File
import kz.alabs.core.base.requestPermission
import kz.alabs.core.base.safeDismiss
import kz.alabs.image_picker.R
import kz.alabs.image_picker.databinding.FragmentGalleryPhotoPickerBinding
import kz.alabs.image_picker.ui.model.UIGalleryPhotoModel
import kz.alabs.image_picker.viewbinder.GalleryPhotoViewBinder

/**
 * Экран с отоброжанием фотографии с галереи
 */

class GalleryPhotoPickerBottomSheetFragment :
    BaseBottomSheetFragment(R.layout.fragment_gallery_photo_picker, isFullScreen = true),
    LoaderManager.LoaderCallbacks<Cursor> {

    companion object {
        private const val PERMISSION_READ_GALLERY = 111
        private const val INIT_LOADER_ID = 21

        fun newInstance(
            selectedImages: List<Uri> = emptyList(),
            isSingleSelectable: Boolean = false,
            onSingleImageClicked: (Uri) -> Unit = {},
            onReadyClicked: (List<Uri>) -> Unit = {}
        ) = GalleryPhotoPickerBottomSheetFragment().apply {
            this.selectedImages.addAll(selectedImages)
            this.onSingleImageClicked = onSingleImageClicked
            this.onReadyClicked = onReadyClicked
            this.isSingleSelectable = isSingleSelectable
        }
    }

    private lateinit var binding: FragmentGalleryPhotoPickerBinding

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(GalleryPhotoViewBinder {
            onImageSelected(it)
        })
    }

    private val imagesList = mutableListOf<String>()
    private var selectedImages = mutableListOf<Uri>()

    private var onSingleImageClicked: (Uri) -> Unit = {}
    private var onReadyClicked: (List<Uri>) -> Unit = {}
    private var isSingleSelectable: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryPhotoPickerBinding.bind(view)
        setupView()
        makePermission()
    }

    private fun setupView() = with(binding) {
        recyclerView.adapter = multiTypeAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        cancelTextView.setOnClickListener {
            safeDismiss()
        }

        readyTextView.setOnClickListener {
            if (isSingleSelectable) {
                if (selectedImages.isNotEmpty()) {
                    onSingleImageClicked(selectedImages.first())
                }
            } else {
                onReadyClicked(selectedImages)
            }
            safeDismiss()
        }
    }

    private fun onImageSelected(model: UIGalleryPhotoModel) {
        if (isSingleSelectable) {
            selectedImages.clear()
        }

        if (model.isSelected) {
            selectedImages.add(model.uri)
        } else {
            selectedImages.remove(model.uri)
        }

        if (isSingleSelectable) {
            multiTypeAdapter.items = mapImageModel()
            multiTypeAdapter.notifyDataSetChanged()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_READ_GALLERY && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(INIT_LOADER_ID, null, this)
        }
    }

    private fun makePermission() {
        requestPermission(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), PERMISSION_READ_GALLERY
        ) {
            if (it) {
                LoaderManager.getInstance(this).initLoader(INIT_LOADER_ID, null, this)
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media._ID
        )
        val sortOrder = MediaStore.Images.Media._ID

        return CursorLoader(
            requireContext(),
            uri,
            projection,
            null,
            null,
            sortOrder
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {}

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val listOfImages = ArrayList<String>()
        data?.let {
            val columnIndexData = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

            while (it.moveToNext()) {
                listOfImages.add(it.getString(columnIndexData));
            }
        }

        if (listOfImages.isNotEmpty()) {
            imagesList.clear()
            imagesList.addAll(listOfImages)
        }

        if (imagesList.isNotEmpty()) {
            multiTypeAdapter.items = mapImageModel()
            multiTypeAdapter.notifyDataSetChanged()
        }
    }

    private fun mapImageModel() = imagesList.map {
        val uri = Uri.fromFile(File(it))
        val isSelected = selectedImages.contains(uri)

        UIGalleryPhotoModel(uri = uri, isSelected)
    }
}