package kz.alabs.file_manager.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import kz.alabs.compressor.compressImage
import kz.alabs.compressor.generateUUID
import kz.alabs.compressor.utils.Constants.COMPRESSED_FILE_NAME
import kz.alabs.file_manager.databinding.ActivityImageCompressorBinding
import kz.alabs.file_manager.utils.Constants.IMAGE_REQUEST_CODE

class ImageCompressorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageCompressorBinding
    private var selectedImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageCompressorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnChooseImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null && requestCode == IMAGE_REQUEST_CODE) {
            selectedImage = getRealPathFromURI(data.data, this)
            val compressedImage = compressImage(
                originFilePath = selectedImage ?: return,
                localFilePath = this.filesDir.absolutePath,
                filename = COMPRESSED_FILE_NAME.format(generateUUID()),
                maxSizeInMb = 1
            )
            binding.ivBefore.setImageURI(compressedImage.toUri())
            val fileSize: Double =
                java.lang.String.valueOf(compressedImage.length() / 1024).toDouble()
            binding.tvSize.text = "Size in mb: ${(fileSize / 1024)}"
        }
    }

    private fun getRealPathFromURI(contentURI: Uri?, context: Activity): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.managedQuery(
            contentURI, projection, null,
            null, null
        ) ?: return null
        val column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        return if (cursor.moveToFirst()) {
            // cursor.close();
            cursor.getString(column_index)
        } else null
        // cursor.close();
    }
}
