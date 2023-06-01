package kz.alabs.image_picker.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kz.alabs.core.base.CoreActivity
import kz.alabs.image_picker.R

class ImagePickerActivity : CoreActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        navController = findNavController(R.id.nav_host_fragment)
    }
}