package com.alix01z.noteappkotlin_mvvm.adapters

import android.widget.AdapterView.OnItemClickListener
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity

interface CardClickListener {
    fun onItemClickListener(noteEntity: NoteEntity)
}