package kz.noxiq.filemanager.image_preview.adapter

import android.os.Bundle
import android.view.View
import kz.noxiq.core.base.BaseFragment
import kz.noxiq.core.base.loadImage
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.FragmentImageBinding

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