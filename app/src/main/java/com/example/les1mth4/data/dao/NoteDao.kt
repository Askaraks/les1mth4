package com.example.les1mth4.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.les1mth4.data.model.NoteModel

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteModel ORDER BY title ASC")
    fun getAllNote(): List<NoteModel>

    @Insert()
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

}