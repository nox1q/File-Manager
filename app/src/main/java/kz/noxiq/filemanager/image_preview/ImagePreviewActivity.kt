package kz.noxiq.filemanager.image_preview

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import kz.noxiq.core.base.UIActivityAnimation
import kz.noxiq.core.base.loadImage
import kz.noxiq.core.base.showActivity
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.ActivityImagePreviewBinding
import kz.noxiq.filemanager.utils.ARGConstants
import kz.noxiq.filemanager.utils.ARGConstants.ARG_IMAGES
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagePreviewActivity : AppCompatActivity(R.layout.activity_image_preview) {

    private lateinit var binding: ActivityImagePreviewBinding
    private val viewModel: ImagePreviewViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        observeViewModel()
        viewModel.onViewCreated()
    }

    private fun observeViewModel() = with(viewModel) {
        initImages.observe(this@ImagePreviewActivity) {
            binding.eventImagesViewPager.adapter = EventImageViewPagerAdapter(it, this@ImagePreviewActivity.supportFragmentManager)
        }
    }

    private fun setupView() = with(binding) {
        eventImagesViewPager.setOnViewPagerClickListener {
            viewModel.localEventFullData?.let {
                showActivity(
                    ImageGalleryActivity(),
                    bundleOf(
                        ARG_IMAGES to it.map { it.uri },
                        ARGConstants.ARG_IMAGE_POSITION to eventImagesViewPager.currentItem
                    ),
                    UIActivityAnimation.RIGHT
                )
            }
        }
    }
}