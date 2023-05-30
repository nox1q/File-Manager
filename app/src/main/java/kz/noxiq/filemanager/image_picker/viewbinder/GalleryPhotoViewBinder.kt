package kz.noxiq.filemanager.image_picker.viewbinder

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kz.noxiq.core.adapter.ItemViewBinder
import kz.noxiq.core.base.loadImage
import kz.noxiq.core.util.ParameterViewHolder
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.ItemGalleryPhotoBinding
import kz.noxiq.filemanager.image_picker.ui.model.UIGalleryPhotoModel

class GalleryPhotoViewBinder(
    private val onPhotoClicked: (UIGalleryPhotoModel) -> Unit
) : ItemViewBinder<UIGalleryPhotoModel, GalleryPhotoViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: UIGalleryPhotoModel) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(ItemGalleryPhotoBinding.inflate(inflater, parent, false).root)

    inner class ViewHolder(itemView: View) : ParameterViewHolder<UIGalleryPhotoModel>(itemView) {

        private val binding = ItemGalleryPhotoBinding.bind(itemView)
        private var item: UIGalleryPhotoModel? = null

        init {
            setupListeners()
        }

        override fun bind(item: UIGalleryPhotoModel) {
            super.bind(item)
            this@ViewHolder.item = item

            binding.checkImageView.isVisible = item.isSelected
            binding.imageView.loadImage(item.uri)
            checkImage(item)
        }

        private fun setupListeners() = with(binding) {
            imageView.setOnClickListener {
                item?.let {
                    it.isSelected = !it.isSelected

                    checkImage(it)
                    onPhotoClicked(it)
                }
            }
        }

        private fun checkImage(item: UIGalleryPhotoModel) = with(binding) {
            checkImageView.isVisible = item.isSelected
        }
    }
}