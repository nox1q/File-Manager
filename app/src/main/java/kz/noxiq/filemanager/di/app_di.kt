package kz.noxiq.filemanager.di

import kz.noxiq.filemanager.image_picker.ImagePickerViewModel
import kz.noxiq.filemanager.image_preview.ImagePreviewViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { ImagePickerViewModel(application = androidApplication()) }
    viewModel { ImagePreviewViewModel(application = androidApplication()) }
}