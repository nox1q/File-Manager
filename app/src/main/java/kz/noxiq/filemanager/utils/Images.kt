package kz.noxiq.filemanager.utils

import kz.noxiq.filemanager.image_picker.ui.model.EventItemImageItemDVO

object Images {
    fun images(): List<EventItemImageItemDVO> = listOf(
        EventItemImageItemDVO(uri = "https://picsum.photos/id/0/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/1/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/2/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/3/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/4/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/5/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/6/367/267",isMain = false),
        EventItemImageItemDVO(uri = "https://picsum.photos/id/7/367/267",isMain = false),
    )
}