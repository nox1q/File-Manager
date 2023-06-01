package kz.alabs.image_picker.ui.dialog

import android.os.Bundle
import android.view.View
import kz.alabs.core.base.BaseBottomSheetFragment
import kz.alabs.image_picker.R
import kz.alabs.image_picker.databinding.DialogAddPhotoBinding
import kz.alabs.image_picker.ui.model.UIAddPhotoAction

/**
 * Диалог с выбором
 *      1.сделать фото с камеры
 *      2.выбор с галереи
 */

class AddPhotoBottomSheetDialog : BaseBottomSheetFragment(R.layout.dialog_add_photo) {

    companion object {
        fun newInstance(onActionClicked: (String) -> Unit) = AddPhotoBottomSheetDialog().apply {
            this.onActionClicked = onActionClicked
        }
    }

    private lateinit var binding: DialogAddPhotoBinding

    private var onActionClicked: (String) -> Unit = {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddPhotoBinding.bind(view)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        takePhotoTextView.setOnClickListener {
            onActionClicked(UIAddPhotoAction.TAKE_PHOTO.name)
            dismiss()
        }

        selectFromGalleryTextView.setOnClickListener {
            onActionClicked(UIAddPhotoAction.SELECT_FROM_GALLERY.name)
            dismiss()
        }

        cancelTextView.setOnClickListener {
            dismiss()
        }
    }
}