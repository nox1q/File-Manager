package kz.noxiq.filemanager.image_picker

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kz.noxiq.core.base.CoreActivity
import kz.noxiq.filemanager.R
import kz.noxiq.filemanager.databinding.ActivityImagePickerBinding

class ImagePickerActivity : CoreActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        navController = findNavController(R.id.nav_host_fragment)
    }
}