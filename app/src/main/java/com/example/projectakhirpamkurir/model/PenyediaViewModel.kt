package com.example.projectakhirpamkurir.model

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiKurir().container.repositoriKurir)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKurir().container.repositoriKurir
            )
        }
        initializer {
            EntryViewModel(aplikasiKurir().container.repositoriKurir)
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiKurir().container.repositoriKurir,
            )
        }
    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari [AplikasiKurir].
 */
fun CreationExtras.aplikasiKurir(): AplikasiKurir =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKurir)
