package kz.alabs.core.base

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.alabs.core_file_manager.R

open class RoundedBottomSheetDialogFragment(
    private val style: Int = R.style.BottomSheetDialogTheme
) : BottomSheetDialogFragment() {

    override fun getTheme(): Int = style

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)
}