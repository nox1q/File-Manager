package kz.alabs.image_preview.ui.image_preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kz.alabs.image_preview.ui.image_preview.EventImageFragment

class EventImageViewPagerAdapter(
    private val images: List<String>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Fragment {
        return EventImageFragment(images[position])
    }

}