package kz.noxiq.core.base

import androidx.fragment.app.Fragment
import kz.noxiq.core.adapter.IKDispatcher
import kz.noxiq.core.util.CoreConstant.PERMISSION_DENIED

abstract class CoreFragment(
    id: Int
) : Fragment(id), PermissionHandler, IKDispatcher {

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            when {
                it != PERMISSION_DENIED -> {
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