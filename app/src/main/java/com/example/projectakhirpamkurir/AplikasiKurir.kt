package com.example.projectakhirpamkurir

import android.app.Application
import com.example.projectakhirpamkurir.repositori.ContainerApp
import com.example.projectakhirpamkurir.repositori.ContainerDataApp

class AplikasiKurir : Application() {
    //AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}