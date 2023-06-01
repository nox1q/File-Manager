package kz.alabs.image_picker.di

import kz.alabs.image_picker.ui.ImagePickerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imagePickerModule = module {
    viewModel { ImagePickerViewModel(application = androidApplication()) }
}