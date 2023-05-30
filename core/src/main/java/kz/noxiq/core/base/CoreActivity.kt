package kz.noxiq.core.base

import androidx.appcompat.app.AppCompatActivity
import kz.noxiq.core.adapter.IKDispatcher
import kz.noxiq.core.util.CoreConstant

abstract class CoreActivity : AppCompatActivity(), PermissionHandler, IKDispatcher {

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
}