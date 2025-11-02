package com.miuh.clubs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miuh.clubs.domain.ClubsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClubsViewModel(
    private val repository: ClubsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<String>())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val clubs = repository.searchClubs()
            _state.update { clubs }
        }
    }

}