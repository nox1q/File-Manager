package kz.noxiq.filemanager.image_preview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ImagesViewPagerAdapter(
    private val images: List<String>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = images.size

    override fun getItem(position: Int): Fragment {
        return ImageFragment(images[position])
    }
}