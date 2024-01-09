package com.example.projectakhirpamkurir.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpamkurir.DetailsDestination
import com.example.projectakhirpamkurir.repositori.RepositoriKurir
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel {class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKurir: RepositoriKurir
) : ViewModel() {

    private val kurirId: Int = checkNotNull(savedStateHandle[DetailsDestination.kurirIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoriKurir.getKurirStream(kurirId)
            .filterNotNull()
            .map {
                ItemDetailUiState(detailKurir = it.toDetailKurir())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )
    suspend fun deleteItem() {
        repositoriKurir.deleteKurir(uiState.value.detailKurir.toKurir())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

}
data class ItemDetailUiState(
    val outOfStock: Boolean = true,
    val detailKurir: DetailKurir = DetailKurir()
)