package com.alix01z.noteappkotlin_mvvm.repository

import com.alix01z.noteappkotlin_mvvm.room.AppDatabase
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepository
@Inject
constructor(appDatabase: AppDatabase){
    private val roomDao = appDatabase.roomDao()

    fun insertNote(noteEntity: NoteEntity) {
        roomDao.insertNote(noteEntity)
    }
    fun getAllNotes():Flow<List<NoteEntity>> {
        return roomDao.getAllNotes()
    }
    fun updateNote(noteEntity: NoteEntity){
        roomDao.updateNote(noteEntity)
    }

    fun deleteNote(noteEntity: NoteEntity){
        roomDao.deleteNote(noteEntity)
    }
}