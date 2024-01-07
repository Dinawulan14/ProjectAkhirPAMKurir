package com.example.projectakhirpamkurir.repositori

import com.example.projectakhirpamkurir.data.Kurir
import com.example.projectakhirpamkurir.data.KurirDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriKurir(private val kurirDao: KurirDao) : RepositoriKurir {
    override fun getAllKurirStream(): Flow<List<Kurir>> = kurirDao.getAllKurir()

    override fun getKurirStream(id: Int): Flow<Kurir?> {
        return kurirDao.getKurir(id)
    }

    override suspend fun insertKurir(kurir: Kurir) = kurirDao.insert(kurir)
    override suspend fun deleteKurir(kurir: Kurir) = kurirDao.delete(kurir)

    override suspend fun updateKurir(kurir: Kurir) {
        kurirDao.update(kurir)
    }
}