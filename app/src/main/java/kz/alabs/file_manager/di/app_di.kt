package kz.alabs.file_manager.di

import kz.alabs.image_picker.di.imagePickerModule
import kz.alabs.image_preview.di.imagePreviewModule

fun appComponent() = arrayListOf(
    imagePickerModule,
    imagePreviewModule
)