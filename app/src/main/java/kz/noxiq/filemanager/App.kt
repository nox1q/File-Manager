package kz.noxiq.filemanager

import android.app.Application
import kz.noxiq.filemanager.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModules))
        }
    }

}