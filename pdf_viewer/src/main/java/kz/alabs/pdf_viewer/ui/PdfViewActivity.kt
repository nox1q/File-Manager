package kz.alabs.pdf_viewer.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kz.alabs.pdf_viewer.databinding.ActivityPdfViewerBinding

class PdfViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfViewerBinding

    private val openLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            try {
                uri?.let { openFile(it) }
            } catch (e: Exception) {
            }
        }

    private fun openFile(uri: Uri) {
        contentResolver.openInputStream(uri)?.use {
            binding.pdfView.fromBytes(it.readBytes()).load()
        } ?: throw IllegalStateException("Can't open input stream")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPdf.setOnClickListener { openLauncher.launch(arrayOf("application/pdf")) }
    }
}