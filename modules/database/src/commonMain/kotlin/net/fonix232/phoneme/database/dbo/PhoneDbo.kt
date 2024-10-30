package net.fonix232.phoneme.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "phones")
data class PhoneDbo(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double? = null,
    @ColumnInfo(name = "color") val color: String? = null,
    @ColumnInfo(name = "capacity") val capacity: String? = null,
    @ColumnInfo(name = "generation") val generation: String? = null,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: Instant? = null
)
