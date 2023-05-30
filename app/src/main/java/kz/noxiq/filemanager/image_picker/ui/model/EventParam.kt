package kz.noxiq.filemanager.image_picker.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File
import java.time.LocalDate

@Parcelize
data class CreateNewEventParam(
    var categoryId: String? = null,
    var title: String? = null,
    var date: LocalDate? = null,
    var time: String? = null,
    var description: String? = null,
    var address: CreatNewEventAddressParam? = null,
    var phoneNumbers: List<String> = emptyList(),
    var emails: List<String> = emptyList(),
    var images: List<CreateNewEventImageParam> = emptyList(),
    var iban: String? = null
) : Parcelable

@Parcelize
data class CreatNewEventAddressParam(
    var address: String? = null,
    var longitude: String? = null,
    var latitude: String? = null,
    var cityId: String? = null
) : Parcelable

@Parcelize
data class CreateNewEventImageParam(
    val photo: File,
    val isMain: Boolean
) : Parcelable