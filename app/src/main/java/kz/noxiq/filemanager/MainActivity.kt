package kz.noxiq.filemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.noxiq.filemanager.databinding.ActivityMainBinding
import kz.noxiq.filemanager.image_picker.ImagePickerActivity
import kz.noxiq.filemanager.image_preview.ImagePreviewActivity

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
            startActivity(Intent(this@MainActivity, ImagePreviewActivity::class.java))
        }
    }
}