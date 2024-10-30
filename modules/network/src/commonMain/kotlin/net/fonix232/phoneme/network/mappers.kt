package net.fonix232.phoneme.network

import kotlinx.datetime.Clock
import net.fonix232.phoneme.network.dto.PhoneDto
import net.fonix232.phoneme.shared.model.Phone

fun PhoneDto.toPhone(): Phone = Phone(
    id = id,
    name = name,
    price = data?.price,
    color = data?.color,
    capacity = (data?.capacity ?: data?.capacityGb?.let { "${this}GB" }),
    generation = data?.generation,
    lastUpdated = Clock.System.now()
)

fun Phone.toDto(): PhoneDto = PhoneDto(
    id = id.toInt(),
    name = name,
    data = net.fonix232.phoneme.network.dto.PhoneDataDto(
        color = color,
        capacity = capacity,
        price = price?.toDouble()
    )
)
