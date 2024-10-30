package net.fonix232.phoneme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.fonix232.phoneme.repositories.PhoneRepository
import net.fonix232.phoneme.shared.model.Phone

class PhoneListViewModel(private val phoneRepository: PhoneRepository): ViewModel() {
    val phones = phoneRepository.phones
    val state = phoneRepository.state

    fun refreshPhones() {
        viewModelScope.launch {
            phoneRepository.fetchPhones()
        }
    }

    fun addPhone(phoneId: Int) {
        viewModelScope.launch {
            phoneRepository.insertPhone(
                Phone(
                    id = phoneId + 1,
                    name = "Test Phone ${phoneId + 1}",
                    price = 100.0,
                    color = "blue"
                )
            )
        }
    }
}