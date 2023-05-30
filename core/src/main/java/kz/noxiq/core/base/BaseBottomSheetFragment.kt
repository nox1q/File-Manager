package kz.noxiq.core.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kz.noxiq.core.R


open class BaseBottomSheetFragment(
    lay: Int,
    private val isFullScreen: Boolean = false,
    private val closeWhenCollapsed: Boolean = false
) : CoreBottomSheetFragment(lay) {

    override fun getTheme() = R.style.TransparentBottomSheetDialogHeight

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = if (isFullScreen) {
        showFullScreen(super.onCreateDialog(savedInstanceState))
    } else super.onCreateDialog(savedInstanceState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBottomSheetCallback()
    }

    private fun addBottomSheetCallback() {
        val dialog = dialog as? BottomSheetDialog
        val behavior = dialog?.behavior

        behavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (closeWhenCollapsed && newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    safeDismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }
}