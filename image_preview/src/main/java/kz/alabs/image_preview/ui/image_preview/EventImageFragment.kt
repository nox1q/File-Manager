package kz.alabs.image_preview.ui.image_preview

import android.os.Bundle
import android.view.View
import kz.alabs.core.base.BaseFragment
import kz.alabs.core.base.loadImage
import kz.alabs.image_preview.R
import kz.alabs.image_preview.databinding.FragmentEventImageBinding

class EventImageFragment(
    private val imageUrl: String
) : BaseFragment(R.layout.fragment_event_image) {

    private lateinit var binding: FragmentEventImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEventImageBinding.bind(view)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        bannerImageView.loadImage(imageUrl)
    }
}