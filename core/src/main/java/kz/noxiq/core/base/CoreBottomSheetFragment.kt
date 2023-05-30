package kz.noxiq.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.noxiq.core.R
import kz.noxiq.core.adapter.IKDispatcher
import kz.noxiq.core.util.CoreConstant

open class CoreBottomSheetFragment(
    private val lay: Int = -1,
    style: Int = R.style.BottomSheetDialogTheme
) :
    RoundedBottomSheetDialogFragment(style),
    PermissionHandler, IKDispatcher {

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            when {
                it != CoreConstant.PERMISSION_DENIED -> {
                    confirmPermission()
                    confirmWithRequestCode(requestCode)
                    return
                }
                else -> {
                    ignorePermission()
                    return
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (lay == -1) {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
        return inflater.inflate(lay, container, false)
    }
}