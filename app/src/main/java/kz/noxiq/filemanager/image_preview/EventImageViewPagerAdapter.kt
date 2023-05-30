package kz.noxiq.filemanager.image_preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kz.noxiq.filemanager.image_picker.ui.model.EventItemImageItemDVO

class EventImageViewPagerAdapter(
    private val images: List<EventItemImageItemDVO>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Fragment {
        return EventImageFragment(images[position].uri)
    }

}