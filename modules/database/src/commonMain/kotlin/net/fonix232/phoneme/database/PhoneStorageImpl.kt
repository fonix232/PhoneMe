package net.fonix232.phoneme.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.fonix232.phoneme.shared.database.PhoneStorage
import net.fonix232.phoneme.shared.model.Phone

class PhoneStorageImpl(private val db: PhoneMeAppDb): PhoneStorage {

    override fun getPhonesFlow(): Flow<List<Phone>> {
        return db.phoneDao().getPhonesFlow().map { phoneList -> phoneList.map { phoneDbo -> phoneDbo.toPhone() } }
    }

    override suspend fun getPhones(): List<Phone> {
        return db.phoneDao().getPhones().map { phoneDbo -> phoneDbo.toPhone() }
    }

    override suspend fun getPhonesById(id: Number): Flow<Phone> {
        return db.phoneDao().getPhoneByIdFlow(id.toInt()).map { phoneDbo -> phoneDbo.toPhone() }
    }

    override suspend fun insertPhone(phone: Phone) {
        db.phoneDao().insertPhone(phone.toDbo())
    }

    override suspend fun insertPhones(phones: List<Phone>) {
        phones.forEach {
            db.phoneDao().insertPhone(it.toDbo())
        }
    }

    override suspend fun deletePhoneById(id: Int) {
        db.phoneDao().deletePhoneById(id)
    }

    override suspend fun cleanUp() {
        db.phoneDao().deleteAllPhones()
    }
}
