package net.fonix232.phoneme.shared.model

import kotlinx.datetime.Instant

data class Phone(
    val id: Number,
    val name: String,
    val price: Number? = null,
    val color: String? = null,
    val capacity: String? = null,
    val generation: String? = null,
    val lastUpdated: Instant? = null
)
