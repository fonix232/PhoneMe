package net.fonix232.phoneme.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import net.fonix232.phoneme.database.dao.PhoneDao
import net.fonix232.phoneme.database.dao.TodoDao
import net.fonix232.phoneme.database.dbo.PhoneDbo
import net.fonix232.phoneme.database.dbo.TodoDbo
import net.fonix232.phoneme.database.utils.InstantConverter

@Database(
    entities = [PhoneDbo::class, TodoDbo::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [ AutoMigration(from = 1, to = 2), AutoMigration(from = 2, to = 3)]
)
@TypeConverters(InstantConverter::class)
abstract class PhoneMeAppDb : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
    abstract fun todoDao(): TodoDao

    companion object {
        internal fun buildDatabase(builder: RoomDatabase.Builder<PhoneMeAppDb>): PhoneMeAppDb =
            builder
                .fallbackToDestructiveMigrationOnDowngrade(true)
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
    }
}

expect class AppDbBuilderHolder() {
    val builder: RoomDatabase.Builder<PhoneMeAppDb>
}
