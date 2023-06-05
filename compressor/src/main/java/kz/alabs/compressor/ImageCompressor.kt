package kz.alabs.compressor

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

@SuppressLint("SdCardPath")
fun compressImage(originFilePath: String, localFilePath: String, filename: String, maxSizeInMb: Int): File {

    val avatarFile = File(localFilePath, filename)
    val maxSize = maxSizeInMb * 1024 * 1024

    if (!avatarFile.exists()) {
        avatarFile.createNewFile()
    }

    File(originFilePath).copyTo(File(avatarFile.absolutePath), true)

    val ei = ExifInterface(avatarFile.absolutePath)
    val orientation: Int = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    if (avatarFile.length() > maxSize) {
        var streamLength = maxSize
        var compressQuality = 105
        val bmpStream = ByteArrayOutputStream()
        while (streamLength >= maxSize && compressQuality > 5) {
            bmpStream.use {
                it.flush()
                it.reset()
            }

            compressQuality -= 15

            val bitmap = getImageWithOrientation(
                BitmapFactory.decodeFile(
                    avatarFile.absolutePath,
                    BitmapFactory.Options()
                ), orientation
            )

            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)

            val bmpPicByteArray = bmpStream.toByteArray()

            streamLength = bmpPicByteArray.size
        }


        FileOutputStream(avatarFile).use {
            it.write(bmpStream.toByteArray())
        }
    }

    return avatarFile
}

fun getImageWithOrientation(bitmap: Bitmap, orientation: Int): Bitmap =

    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(
            bitmap,
            90f
        )
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(
            bitmap,
            180f
        )
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(
            bitmap,
            270f
        )
        else -> bitmap
    }

fun rotateImage(bitmap: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun generateUUID() = UUID.randomUUID().toString()