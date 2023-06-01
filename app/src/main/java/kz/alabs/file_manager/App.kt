package kz.alabs.file_manager

import android.app.Application
import kz.alabs.file_manager.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appComponent())
        }
    }
}