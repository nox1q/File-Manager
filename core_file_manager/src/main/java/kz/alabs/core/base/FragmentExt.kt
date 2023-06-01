package kz.alabs.core.base

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import kz.alabs.core.util.CoreConstant.EMPTY
import kz.alabs.core.util.CoreFileConstants
import kz.alabs.core.util.Permission
import kz.alabs.core.util.PermissionImpl
import kz.alabs.core.util.RequestCodeConstants
import java.io.File
import java.io.IOException

/**
 * Открываем стандартную камеру делает снимок и возвращает файл
 */
fun Fragment.showCameraForPictureImage(authority: String): File {
    var file = File(EMPTY)
    val fileName = "JPEG_${System.currentTimeMillis()}_"
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(activity?.packageManager ?: return@also)?.also {
            file = try {
                activity!!.createFile(
                    fileName,
                    CoreFileConstants.EXTENSION_JPG
                )
            } catch (ex: IOException) {
                File(EMPTY)
            }
            file.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    activity!!,
                    authority,
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(
                    takePictureIntent,
                    RequestCodeConstants.TAKE_PHOTO
                )
            }
        }
    }
    return file
}

fun Fragment.requestPermission(
    permissionList: Array<String>,
    request: Int,
    isPermission: ((Boolean) -> Unit?)? = null
) {
    var permission: Permission? = null

    if (permission == null) {
        permission = PermissionImpl(fragment = this)
    }

    permission.apply {
        if (isPermission == null) {
            permissionExistFragment(permissionList, request)
            return
        }
        isPermission.invoke(permissionExistFragment(permissionList, request))
    }
}