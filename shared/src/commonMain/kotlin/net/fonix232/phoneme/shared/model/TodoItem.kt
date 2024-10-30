package net.fonix232.phoneme.shared.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class TodoItem @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val lastUpdated: Instant = Clock.System.now()
)
