package kz.noxiq.core.base

interface PermissionHandler {

    fun confirmPermission() {
        // do nothing
    }


    fun confirmWithRequestCode(requestCode: Int) {
        // do nothing
    }


    fun ignorePermission() {
        // do nothing
    }
}