package net.fonix232.phoneme.shared.database

import kotlinx.coroutines.flow.Flow
import net.fonix232.phoneme.shared.model.Phone

interface PhoneStorage {
    fun getPhonesFlow(): Flow<List<Phone>>
    suspend fun getPhones(): List<Phone>
    suspend fun getPhonesById(id: Number): Flow<Phone>
    suspend fun insertPhone(phone: Phone)
    suspend fun insertPhones(phones: List<Phone>)
    suspend fun deletePhoneById(id: Int)
    suspend fun cleanUp()
}
