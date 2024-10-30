package net.fonix232.phoneme.database.utils

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {

    @TypeConverter
    fun fromIso8601String(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }

    @TypeConverter
    fun toIso8601String(value: Instant?): String? {
        return value?.toString()
    }

}