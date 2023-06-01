package kz.alabs.image_picker.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.alabs.core.adapter.ItemViewBinder
import kz.alabs.core.base.loadImage
import kz.alabs.core.util.ParameterViewHolder
import kz.alabs.image_picker.databinding.ItemSelectedEventPhotoBinding
import kz.alabs.image_picker.ui.model.UISelectedEventPhoto

class SelectedEventPhotoViewBinder(
    private val onRemoveClicked: (UISelectedEventPhoto) -> Unit
) : ItemViewBinder<UISelectedEventPhoto, SelectedEventPhotoViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: UISelectedEventPhoto) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(ItemSelectedEventPhotoBinding.inflate(inflater, parent, false).root)

    inner class ViewHolder(itemView: View) : ParameterViewHolder<UISelectedEventPhoto>(itemView) {

        private val binding get() = ItemSelectedEventPhotoBinding.bind(itemView)
        private lateinit var item: UISelectedEventPhoto

        init {
            binding.removeImageView.setOnClickListener {
                onRemoveClicked(item)
            }
        }

        override fun bind(item: UISelectedEventPhoto) = with(binding) {
            super.bind(item)
            this@ViewHolder.item = item

            imageView.loadImage(item.photo)
        }
    }
}