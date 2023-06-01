package kz.alabs.image_preview.ui.image_gallery

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.alabs.core.base.scrollListener
import kz.alabs.core.util.RequestCodeConstants
import kz.alabs.core.util.RequestCodeConstants.RESULT_IMAGE_POSITION
import kz.alabs.image_preview.R
import kz.alabs.image_preview.databinding.ActivityImagesBinding
import kz.alabs.image_preview.utils.ARGConstants

/**
 * Подрбный экран с картинок в приблежонном виде
 */

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
            val intent = Intent()
            intent.putExtra(RESULT_IMAGE_POSITION, imagesViewPager.currentItem)
            setResult(RESULT_OK, intent)
            finish()
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