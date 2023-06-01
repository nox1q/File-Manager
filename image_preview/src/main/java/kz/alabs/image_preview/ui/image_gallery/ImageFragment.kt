package kz.alabs.image_preview.ui.image_gallery

import android.os.Bundle
import android.view.View
import kz.alabs.core.base.BaseFragment
import kz.alabs.core.base.loadImage
import kz.alabs.image_preview.R
import kz.alabs.image_preview.databinding.FragmentImageBinding

class ImageFragment(
    private val imageUrl: String
) : BaseFragment(R.layout.fragment_image) {

    private lateinit var binding: FragmentImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        bannerImageView.loadImage(imageUrl)
    }
}