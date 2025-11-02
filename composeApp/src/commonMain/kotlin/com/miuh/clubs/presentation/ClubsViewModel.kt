package com.miuh.clubs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClubsViewModel(
    private val top100uc: NetworkingUseCase<List<String>>
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<String>())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val clubs = top100uc()
            _state.update { clubs }
        }
    }

}