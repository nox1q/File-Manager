package kz.noxiq.filemanager.image_preview

import android.os.Bundle
import android.view.View
import kz.noxiq.core.base.BaseFragment
import kz.noxiq.core.base.loadImage
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.FragmentEventImageBinding

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