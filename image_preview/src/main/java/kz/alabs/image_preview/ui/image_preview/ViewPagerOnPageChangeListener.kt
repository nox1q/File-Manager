package kz.alabs.image_preview.ui.image_preview

import androidx.viewpager.widget.ViewPager

class ViewPagerOnPageChangeListener(private val onChange: (Int) -> Unit): ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        onChange.invoke(position)
    }

    override fun onPageScrollStateChanged(state: Int) {}
}