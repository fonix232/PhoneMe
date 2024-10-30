package net.fonix232.phoneme

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModules)
    }
}