package kz.alabs.core.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import kz.alabs.core.util.ActivityPermissionImpl
import kz.alabs.core.util.CoreConstant
import kz.alabs.core.util.CoreConstant.ARG_ANIM
import kz.alabs.core.util.CoreFileConstants
import kz.alabs.core.util.Permission
import kz.alabs.core.util.RequestCodeConstants.ON_ACTIVITY_RESULT
import kz.alabs.core.util.RequestCodeConstants.TAKE_PHOTO
import kz.alabs.core_file_manager.R
import java.io.File
import java.io.IOException

fun Activity.showCameraForPictureImage(authority: String): File {
    var file = File("")
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager ?: return@also)?.also {
            file = try {
                createFile("JPEG_${System.currentTimeMillis()}_", CoreFileConstants.EXTENSION_JPG)

            } catch (ex: IOException) {
                File(CoreConstant.EMPTY)
            }
            file.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    authority,
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, TAKE_PHOTO)
            }
        }
    }
    return file
}

fun AppCompatActivity.requestPermission(
    permissionList: Array<String>,
    request: Int,
    isPermission: ((Boolean) -> Unit?)? = null
) {
    var permission: Permission? = null

    if (permission == null) {
        permission = ActivityPermissionImpl(activity = this)
    }

    permission.apply {
        if (isPermission == null) {
            permissionExistFragment(permissionList, request)
            return
        }
        isPermission.invoke(permissionExistFragment(permissionList, request))
    }
}

fun Activity.showActivity(
    activity: Activity,
    args: Bundle? = null,
    typeAnimation: UIActivityAnimation? = null
) {
    startActivity(Intent(this, activity::class.java).apply {
        args?.let {
            putExtras(it)
        }
        putExtra(ARG_ANIM, typeAnimation)
    })

    if (this is CoreActivity) {
        when (typeAnimation) {
            UIActivityAnimation.BOTTOM -> {
                activity.overridePendingTransition(
                    R.anim.slide_enter_top,
                    R.anim.slide_exit_top
                )
            }
            UIActivityAnimation.RIGHT -> {
                activity.overridePendingTransition(
                    R.anim.slide_enter_left,
                    R.anim.slide_exit_left
                )
            }
        }
    }
}

fun Activity.showOnActivityResult(
    activity: Activity,
    args: Bundle? = null,
    typeAnimation: UIActivityAnimation? = null
) {
    startActivityForResult(Intent(this, activity::class.java).apply {
        args?.let {
            putExtras(it)
        }
        putExtra(ARG_ANIM, typeAnimation)
    }, ON_ACTIVITY_RESULT)

    if (this is CoreActivity) {
        when (typeAnimation) {
            UIActivityAnimation.BOTTOM -> {
                activity.overridePendingTransition(
                    R.anim.slide_enter_top,
                    R.anim.slide_exit_top
                )
            }
            UIActivityAnimation.RIGHT -> {
                activity.overridePendingTransition(
                    R.anim.slide_enter_left,
                    R.anim.slide_exit_left
                )
            }
        }
    }
}

fun Activity.openSettings(
    intentAction: String,
    message: String,
    onNegativeButtonAction: () -> Unit
) {
    val intent = Intent(intentAction).apply {
        if (intentAction == Settings.ACTION_APPLICATION_DETAILS_SETTINGS) {
            data = Uri.fromParts("package", packageName, null)
        }
    }
    android.app.AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton("Перейти") { _, _ ->
            startActivity(intent)
        }
        setNegativeButton("Отмена") { _, _ ->
            onNegativeButtonAction.invoke()
        }
        setCancelable(false)
    }.show()
}