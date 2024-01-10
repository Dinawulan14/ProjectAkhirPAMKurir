package com.example.projectakhirpamkurir.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblKurir")
data class Kurir(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val alamat: String,
    val telepon: String,
    val beratbarang: String,
    val statuspengiriman: String,
    val namakurir: String
)
