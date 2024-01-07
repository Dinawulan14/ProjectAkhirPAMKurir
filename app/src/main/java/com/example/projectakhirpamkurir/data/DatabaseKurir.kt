package com.example.projectakhirpamkurir.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kurir::class], version = 1, exportSchema = false)
abstract class DatabaseKurir : RoomDatabase(){
    abstract fun kurirDao() : KurirDao

    companion object {
        @Volatile
        private var Instance: DatabaseKurir? = null

        fun getDatabase(context: Context): DatabaseKurir {
            return (Instance ?: synchronized(this){
                  Room.databaseBuilder(
                    context,
                    DatabaseKurir::class.java,
                    "kurir_database"
                )
                    .build().also { Instance = it }
            })
        }
    }
}