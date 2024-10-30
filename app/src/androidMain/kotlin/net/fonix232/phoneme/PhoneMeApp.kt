package net.fonix232.phoneme

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup.onKoinStartup
import org.koin.core.logger.Level

class PhoneMeApp: Application() {

    init {
        onKoinStartup {
            multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }
            androidContext(this@PhoneMeApp)
            androidLogger(level = Level.DEBUG)
            modules(appModules)
        }
    }
}