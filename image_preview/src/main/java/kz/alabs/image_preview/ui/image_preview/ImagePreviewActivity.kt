package kz.alabs.image_preview.ui.image_preview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import kz.alabs.core.base.*
import kz.alabs.core.util.RequestCodeConstants.RESULT_IMAGE_POSITION
import kz.alabs.image_preview.R
import kz.alabs.image_preview.databinding.ActivityImagePreviewBinding
import kz.alabs.image_preview.ui.image_gallery.ImageGalleryActivity
import kz.alabs.image_preview.utils.ARGConstants.ARG_IMAGES
import kz.alabs.image_preview.utils.ARGConstants.ARG_IMAGE_POSITION
import kz.alabs.image_preview.utils.ARGConstants.ARG_INIT_IMAGES
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Главный экран с картинками
 */

class ImagePreviewActivity : AppCompatActivity(R.layout.activity_image_preview) {

    private lateinit var binding: ActivityImagePreviewBinding
    private val viewModel: ImagePreviewViewModel by viewModel()
    private var imageSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getImages(intent.extras)
        setupView()
        setupAdapter()
        observeViewModel()
    }

    private fun observeViewModel() = with(viewModel) {
        initImages.observe(this@ImagePreviewActivity) {
            binding.eventImagesViewPager.adapter =
                EventImageViewPagerAdapter(it, this@ImagePreviewActivity.supportFragmentManager)
        }
        currentImagePosition.observe(this@ImagePreviewActivity) {
            setPositionText(it + 1)
        }
    }

    private fun setupAdapter() {
        binding.eventImagesViewPager
            .addOnPageChangeListener(ViewPagerOnPageChangeListener {
                viewModel.onPositionChange(it)
            })
    }

    private fun getImages(bundle: Bundle?) {
        if (bundle == null) return
        val list = bundle.getStringArrayList(ARG_INIT_IMAGES)
        if (list != null) {
            viewModel.setInitImages(list)
            imageSize = list.size
        }
    }

    private fun setupView() = with(binding) {
        setPositionText(1)
        eventImagesViewPager.setOnViewPagerClickListener {
            viewModel.listImages?.let {
                showOnActivityResult(
                    ImageGalleryActivity(),
                    bundleOf(
                        ARG_IMAGES to it,
                        ARG_IMAGE_POSITION to eventImagesViewPager.currentItem
                    ),
                    UIActivityAnimation.RIGHT
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        val position = data.getIntExtra(
            RESULT_IMAGE_POSITION,
            binding.eventImagesViewPager.currentItem
        )
        setPositionText(position)
        binding.eventImagesViewPager.currentItem = position
    }

    private fun setPositionText(position: Int) {
        binding.tvPagePosition.text =
            resources.getString(
                R.string.pos_page_from_dp,
                "$position",
                "$imageSize"
            )
    }
}