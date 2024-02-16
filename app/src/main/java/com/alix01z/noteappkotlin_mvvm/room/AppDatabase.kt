package com.alix01z.noteappkotlin_mvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity

@Database(entities = [NoteEntity::class] , version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao() : RoomDao
}