package net.fonix232.phoneme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.fonix232.phoneme.repositories.PhoneRepository
import net.fonix232.phoneme.shared.model.Phone

class PhoneDetailViewModel(
    private val phoneId: Int,
    private val phoneRepository: PhoneRepository
) : ViewModel() {

    private val _phone: MutableStateFlow<Phone?> = MutableStateFlow(null)
    val phone: StateFlow<Phone?>
        get() =_phone

    init {
        viewModelScope.launch {
            if (phoneId > 0) {
                phoneRepository.getPhoneById(phoneId).collect {
                    _phone.value = it
                }
            }
        }
    }

}