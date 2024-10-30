package net.fonix232.phoneme.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PhoneDto(
    val id: Int,
    val name: String,
    val data: PhoneDataDto? = null
)