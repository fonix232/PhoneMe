package net.fonix232.phoneme.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.NativeSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class AppDbBuilderHolder actual constructor() {
    actual val builder: RoomDatabase.Builder<PhoneMeAppDb>
        get() = Room.databaseBuilder<PhoneMeAppDb>(
            name = "$documentDirectory/phoneme.db",
        ).setDriver(NativeSQLiteDriver())

    @OptIn(ExperimentalForeignApi::class)
    private val documentDirectory: String
        get() = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )?.path ?: error("Could not get document directory")

}