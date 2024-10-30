package net.fonix232.phoneme.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.fonix232.phoneme.shared.database.PhoneStorage
import net.fonix232.phoneme.shared.model.Phone
import net.fonix232.phoneme.shared.network.PhoneApi

class PhoneRepository(
    private val api: PhoneApi,
    private val storage: PhoneStorage
) {
    private val _state = MutableStateFlow<RepositoryState>(RepositoryState.IDLE)
    val state: StateFlow<RepositoryState>
        get() = _state.asStateFlow()

    val phones: Flow<List<Phone>>
        get() = storage.getPhonesFlow()

    suspend fun fetchPhones() {
        if(_state.value == RepositoryState.LOADING) return
        setState(RepositoryState.LOADING)
        try {

            val phones = api.getPhoneList()
            storage.insertPhones(phones)
            setState(RepositoryState.IDLE)
        } catch (e: Exception) {
            // TODO: Implement specific error messages
            setState(RepositoryState.ERROR(e))
        }
    }

    suspend fun getPhoneById(id: Number): Flow<Phone> {
        return storage.getPhonesById(id)
    }

    suspend fun insertPhone(phone: Phone) {
        storage.insertPhone(phone)
    }

    private fun setState(state: RepositoryState) {
        _state.value = state
    }

    sealed class RepositoryState {
        data object IDLE: RepositoryState()
        data object LOADING: RepositoryState()
        data class ERROR(val exception: Exception): RepositoryState()

        val isLoading: Boolean
            get() = this == LOADING
    }
}
