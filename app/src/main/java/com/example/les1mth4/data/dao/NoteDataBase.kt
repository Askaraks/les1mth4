package com.example.les1mth4.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.les1mth4.data.model.NoteModel

@Database(entities =[NoteModel::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDataBase(context).also {noteDatabase ->
                INSTANCE = noteDatabase
            }
        }


        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDataBase::class.java,
            "DB_NAME"
        ).allowMainThreadQueries().build()

    }
}