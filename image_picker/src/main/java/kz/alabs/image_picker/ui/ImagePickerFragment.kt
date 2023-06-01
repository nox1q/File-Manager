package kz.alabs.image_picker.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kz.alabs.core.adapter.MultiTypeAdapter
import kz.alabs.core.base.*
import kz.alabs.core.util.RequestCodeConstants
import kz.alabs.image_picker.ui.dialog.AddPhotoBottomSheetDialog
import kz.alabs.image_picker.viewbinder.AddNewEventPhotoViewBinder
import kz.alabs.image_picker.viewbinder.SelectedEventPhotoViewBinder
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import kz.alabs.image_picker.R
import kz.alabs.image_picker.databinding.FragmentImagePickerBinding
import kz.alabs.image_picker.ui.bottom_sheet.GalleryPhotoPickerBottomSheetFragment
import kz.alabs.image_picker.utils.PermissionCodeConstant

class ImagePickerFragment : BaseFragment(R.layout.fragment_image_picker) {

    private lateinit var binding: FragmentImagePickerBinding
    private val viewModel: ImagePickerViewModel by viewModel()
    private var currentPhotoPath: String? = null

    private val multiTypeAdapter = MultiTypeAdapter().apply {
        register(AddNewEventPhotoViewBinder {
            showAddPhotoDialog()
        })
        register(SelectedEventPhotoViewBinder {
            viewModel.onRemovePhoto(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagePickerBinding.bind(view)
        setupAdapter()
        observeViewModel()
        viewModel.onViewCreated()
    }

    private fun setupAdapter() = with(binding) {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = multiTypeAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        contents.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = GridLayoutManager(
                requireContext(),
                if (it.size > 1) 2 else 1
            )

            multiTypeAdapter.items = it
            multiTypeAdapter.notifyDataSetChanged()
        }
        showCamera.observe(viewLifecycleOwner) {
            checkCameraPermission()
        }
        showGallery.observe(viewLifecycleOwner) {
            GalleryPhotoPickerBottomSheetFragment.newInstance(
                selectedImages = selectedPhotosFromGallery.value.orEmpty(),
                onReadyClicked = {
                    viewModel.onPhotoSelected(it)
                }
            ).show(childFragmentManager)
        }
        redirectFragment.observe(viewLifecycleOwner, EventObserver {
            val (path, args) = it
            findNavController().navigate(path, args)
        })
    }

    private fun showAddPhotoDialog() {
        AddPhotoBottomSheetDialog.newInstance {
            viewModel.onActionClicked(it)
        }.show(childFragmentManager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RequestCodeConstants.TAKE_PHOTO -> viewModel.onTakePhoto(currentPhotoPath.orEmpty())
            }
        }
    }

    override fun confirmWithRequestCode(requestCode: Int) {
        when (requestCode) {
            PermissionCodeConstant.CAMERA_PERMISSION_CODE -> {
                currentPhotoPath = showCameraForPictureImage("kz.alabs.image_picker.provider").absolutePath
            }
        }
    }

    override fun ignorePermission() {
        super.ignorePermission()
        activity?.openSettings(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            "Приложение запрашивает доступ к Камере"
        ) {}
    }

    private fun checkCameraPermission() {
        requestPermission(
            arrayOf(
                Manifest.permission.CAMERA
            ), PermissionCodeConstant.CAMERA_PERMISSION_CODE
        ) { permissionProvided ->
            if (permissionProvided) {
                currentPhotoPath = showCameraForPictureImage("kz.nox1q.provider").absolutePath
            }
        }
    }
}