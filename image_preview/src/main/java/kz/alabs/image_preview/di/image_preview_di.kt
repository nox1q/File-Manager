package kz.alabs.image_preview.di

import kz.alabs.image_preview.ui.image_preview.ImagePreviewViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imagePreviewModule = module {
    viewModel { ImagePreviewViewModel(application = androidApplication()) }
}