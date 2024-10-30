package net.fonix232.phoneme.shared.network

import net.fonix232.phoneme.shared.model.Phone

interface PhoneApi {

    suspend fun getPhoneList(): List<Phone>

    suspend fun getPhoneDetails(id: Number): Phone

    suspend fun addPhone(phone: Phone)

    suspend fun updatePhone(id: Number, phone: Phone)

    suspend fun deletePhone(id: Number)

}
