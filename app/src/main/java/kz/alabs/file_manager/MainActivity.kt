package kz.alabs.file_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kz.alabs.file_manager.ui.ImageCompressorActivity
import kz.alabs.file_manager.databinding.ActivityMainBinding
import kz.alabs.file_manager.utils.Images
import kz.alabs.image_picker.ui.ImagePickerActivity
import kz.alabs.image_preview.ui.image_preview.ImagePreviewActivity
import kz.alabs.image_preview.utils.ARGConstants.ARG_INIT_IMAGES
import kz.alabs.pdf_viewer.ui.PdfViewActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnWorkWithImages.setOnClickListener {
            startActivity(Intent(this@MainActivity, ImagePickerActivity::class.java))
        }
        btnViewImages.setOnClickListener {
            val images = Images.images()
            val intent = Intent(this@MainActivity, ImagePreviewActivity::class.java)
            intent.putStringArrayListExtra(ARG_INIT_IMAGES, images)
            startActivity(intent)
        }
        btnCompressor.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageCompressorActivity::class.java)
            startActivity(intent)
        }
        btnPdfViewer.setOnClickListener {
            val intent = Intent(this@MainActivity, PdfViewActivity::class.java)
            startActivity(intent)
        }
    }
}