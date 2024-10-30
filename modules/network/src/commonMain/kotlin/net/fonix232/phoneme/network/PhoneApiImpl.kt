package net.fonix232.phoneme.network

import net.fonix232.phoneme.shared.model.Phone
import net.fonix232.phoneme.shared.network.PhoneApi

class PhoneApiImpl(private val service: PhoneService): PhoneApi {

    override suspend fun getPhoneList(): List<Phone> {
        val result = service.getPhones()
        if(result.isSuccess) return result.getOrThrow().map { it.toPhone() }
        else throw result.exceptionOrNull() ?: Exception("Unknown error")
    }

    override suspend fun getPhoneDetails(id: Number): Phone {
        val result = service.getPhonesById(listOf(id))
        if(result.isSuccess) return result.getOrThrow().map { it.toPhone() }.first()
        else throw result.exceptionOrNull() ?: Exception("Unknown error")
    }

    override suspend fun addPhone(phone: Phone) {
        service.addPhone(phone.toDto())
    }

    override suspend fun updatePhone(id: Number, phone: Phone) {
        service.updatePhone(id, phone.toDto())
    }

    override suspend fun deletePhone(id: Number) {
        service.deletePhone(id)
    }
}
