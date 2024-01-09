package com.example.projectakhirpamkurir.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KurirDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kurir: Kurir)

    @Update
    suspend fun update(kurir: Kurir)

    @Delete
    suspend fun  delete(kurir: Kurir)

    @Query("SELECT * from tblKurir WHERE id = :id")
    fun getKurir(id: Int): Flow<Kurir>

    @Query("SELECT * from tblKurir ORDER BY alamat ASC")
    fun getAllKurir(): Flow<List<Kurir>>
}