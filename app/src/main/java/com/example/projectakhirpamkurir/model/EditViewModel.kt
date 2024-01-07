package com.example.projectakhirpamkurir.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpamkurir.repositori.RepositoriKurir
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriKurir: RepositoriKurir
) : ViewModel() {

    var kurirUiState by mutableStateOf(UIStateKurir())
        private set
        private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            kurirUiState = repositoriKurir.getKurirStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateKurir(true)
        }
    }
    suspend fun updateKurir() {
        if (validasiInput(kurirUiState.detailKurir)) {
            repositoriKurir.updateKurir(kurirUiState.detailKurir.toKurir())
        } else {
            println("Data tidak valid")
        }
    }
    fun updateUiState(detailKurir: DetailKurir) {
        kurirUiState =
            UIStateKurir(detailKurir = detailKurir, isEntryValid = validasiInput(detailKurir))
    }

    private fun validasiInput(uiState: DetailKurir = kurirUiState.detailKurir ): Boolean {
        return with(uiState){
            alamat.isNotBlank() && telepon.isNotBlank() && beratbarang.isNotBlank() && statuspengiriman.isNotBlank() && namakurir.isNotBlank()
        }
    }

}