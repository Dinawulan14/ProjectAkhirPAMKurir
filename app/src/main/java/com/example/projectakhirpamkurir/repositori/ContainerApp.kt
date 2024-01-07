package com.example.projectakhirpamkurir.repositori

import android.content.Context
import com.example.projectakhirpamkurir.data.DatabaseKurir

interface ContainerApp {
    val repositoriKurir : RepositoriKurir
}

class ContainerDataApp(private val context: Context): ContainerApp {
    override val repositoriKurir: RepositoriKurir by lazy {
        OfflineRepositoriKurir(DatabaseKurir.getDatabase(context).kurirDao())
    }
}

