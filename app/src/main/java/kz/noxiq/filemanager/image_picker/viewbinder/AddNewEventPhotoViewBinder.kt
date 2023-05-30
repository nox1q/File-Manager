package kz.noxiq.filemanager.image_picker.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.noxiq.core.adapter.ItemViewBinder
import kz.noxiq.core.util.ParameterViewHolder
import kz.noxiq.filemanager.databinding.ItemAddNewEventPhotoBinding
import kz.noxiq.filemanager.image_picker.ui.model.UIAddNewEventPhoto

class AddNewEventPhotoViewBinder(
    private val onClick: () -> Unit
) : ItemViewBinder<UIAddNewEventPhoto, AddNewEventPhotoViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: UIAddNewEventPhoto) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(ItemAddNewEventPhotoBinding.inflate(inflater, parent, false).root)

    inner class ViewHolder(itemView: View) : ParameterViewHolder<UIAddNewEventPhoto>(itemView) {

        private val binding get() = ItemAddNewEventPhotoBinding.bind(itemView)

        init {
            binding.root.rootView.setOnClickListener {
                onClick()
            }
        }
    }
}