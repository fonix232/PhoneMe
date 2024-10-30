package net.fonix232.phoneme.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.fonix232.phoneme.database.dbo.PhoneDbo

@Dao
interface PhoneDao {

    @Insert
    suspend fun insertPhone(phone: PhoneDbo)

    @Query("SELECT * FROM phones")
    suspend fun getPhones(): List<PhoneDbo>

    @Query("SELECT * FROM phones")
    fun getPhonesFlow(): Flow<List<PhoneDbo>>

    @Query("SELECT * FROM phones WHERE id = :id")
    suspend fun getPhoneById(id: Int): PhoneDbo

    @Query("SELECT * FROM phones WHERE id = :id")
    fun getPhoneByIdFlow(id: Int): Flow<PhoneDbo>

    @Query("DELETE FROM phones WHERE id = :id")
    suspend fun deletePhoneById(id: Int)

    @Query("DELETE FROM phones")
    suspend fun deleteAllPhones()
}
