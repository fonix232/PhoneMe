package net.fonix232.phoneme.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "todo")
data class TodoDbo(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "isDone") val isDone: Boolean = false,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: Instant
)
