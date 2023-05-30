package kz.noxiq.filemanager.image_preview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.noxiq.core.base.scrollListener
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.ActivityImagesBinding
import kz.noxiq.filemanager.image_preview.adapter.ImagesViewPagerAdapter
import kz.noxiq.filemanager.utils.ARGConstants

class ImageGalleryActivity : AppCompatActivity(R.layout.activity_images) {

    private lateinit var binding: ActivityImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() = with(binding) {
        toolbarView.doOnClickMenuIcon {
            onBackPressed()
        }
        intent?.extras?.getStringArrayList(ARGConstants.ARG_IMAGES)?.let { images ->
            binding.imagesViewPager.adapter = ImagesViewPagerAdapter(
                images = images,
                fragmentManager = this@ImageGalleryActivity.supportFragmentManager
            )
            setToolbarSliderStatus(binding.imagesViewPager.currentItem, images.size)
            binding.imagesViewPager.scrollListener {
                setToolbarSliderStatus(it, images.size)
            }
        }
        intent?.extras?.getInt(ARGConstants.ARG_IMAGE_POSITION)?.let {
            binding.imagesViewPager.currentItem = it
        }
    }

    private fun setToolbarSliderStatus(currentPage: Int, totalCount: Int) = with(binding) {
        toolbarView.setTitle(
            resources.getString(
                R.string.page_from_dp,
                "${currentPage + 1}",
                "$totalCount"
            )
        )
    }
}