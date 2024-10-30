package net.fonix232.phoneme.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class AppDbBuilderHolder actual constructor() : KoinComponent {

    private val context: Context by inject()

    actual val builder: RoomDatabase.Builder<PhoneMeAppDb>
        get() = with(context.applicationContext) {
            return Room.databaseBuilder<PhoneMeAppDb>(
                context = this,
                name = getDatabasePath("phoneme.db").absolutePath
            )
        }
}