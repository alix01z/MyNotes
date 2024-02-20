package com.alix01z.noteappkotlin_mvvm.adapters

import android.view.View
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity

interface CardClickListener {
    fun onItemClickListener(noteEntity: NoteEntity)
    fun onOptionClickListener(imgvItemOption : View, noteEntity: NoteEntity)
}