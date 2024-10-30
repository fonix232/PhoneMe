package net.fonix232.phoneme.network.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class PhoneDataDto @OptIn(ExperimentalSerializationApi::class) constructor(
    val color: String? = null,
    @SerialName("capacity") @JsonNames("Capacity", "capacity") val capacity: String? = null,
    @SerialName("Capacity GB") val capacityGb: Double? = null,
    val price: Double? = null,
    val year: Int? = null,
    val generation: String? = null,
    @SerialName("CPU model") val cpuModel: String? = null,
    @SerialName("Hard disk size") val hddSize: String? = null,
    @SerialName("Strap Colour") val strapColour: String? = null,
    @SerialName("Screen size") val screenSize: String? = null,
)
