package com.alix01z.noteappkotlin_mvvm.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM table_note")
    fun getAllNotes() : Flow<List<NoteEntity>>
}