package com.alix01z.noteappkotlin_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alix01z.noteappkotlin_mvvm.repository.MyRepository
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel
@Inject
constructor(private val repository: MyRepository) : ViewModel() {
    private var data : MutableLiveData<List<NoteEntity>> = MutableLiveData()
    var myLiveData : LiveData<List<NoteEntity>> = data

    init {
        getAllNotesFromDB()
    }

    fun insertNoteToDB(noteModel: NoteEntity) {
        //Send Data to Repository using Coroutine
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(noteModel)
        }
    }

    fun getAllNotesFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().collect(){
                data.postValue(it)
            }
        }
    }

    fun updateNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(noteEntity)
        }
    }
}