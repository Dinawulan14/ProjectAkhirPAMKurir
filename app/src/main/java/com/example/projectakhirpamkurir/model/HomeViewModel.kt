package com.example.projectakhirpamkurir.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpamkurir.data.Kurir
import com.example.projectakhirpamkurir.repositori.RepositoriKurir
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel (private val repositoriKurir: RepositoriKurir): ViewModel(){
    companion object {
        private const val TIMEOUT_MILIIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriKurir.getAllKurirStream().filterNotNull()
        .map {HomeUiState(listKurir = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILIIS),
            initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listKurir: List<Kurir> = listOf()
    )
}