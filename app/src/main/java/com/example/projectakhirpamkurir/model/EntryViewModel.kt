package com.example.projectakhirpamkurir.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projectakhirpamkurir.data.Kurir
import com.example.projectakhirpamkurir.repositori.RepositoriKurir



//Mewakili Status UI untuk kurir
data class UIStateKurir(
    val detailKurir: DetailKurir = DetailKurir(),
    val isEntryValid: Boolean = false
)

data class DetailKurir(
    val id: Int = 0,
    val alamat: String ="",
    val telepon: String = "",
    val beratbarang: String = "",
    val statuspengiriman: String = "",
    val namakurir: String = ""
)

fun DetailKurir.toKurir(): Kurir = Kurir(
    id = id,
    alamat = alamat,
    telepon = telepon,
    beratbarang = beratbarang,
    statuspengiriman = statuspengiriman,
    namakurir = namakurir
)

fun Kurir.toUiStateKurir(isEntryValid: Boolean = false): UIStateKurir = UIStateKurir(
    detailKurir = this.toDetailKurir(),
    isEntryValid = isEntryValid
)

fun Kurir.toDetailKurir(): DetailKurir = DetailKurir(
    id = id,
    alamat = alamat,
    telepon = telepon,
    beratbarang = beratbarang,
    statuspengiriman = statuspengiriman,
    namakurir = namakurir
)