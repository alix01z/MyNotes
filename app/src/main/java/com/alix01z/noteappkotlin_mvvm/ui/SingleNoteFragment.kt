package com.alix01z.noteappkotlin_mvvm.ui

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.alix01z.noteappkotlin_mvvm.R
import com.alix01z.noteappkotlin_mvvm.databinding.FragmentSingleNoteBinding
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity
import com.alix01z.noteappkotlin_mvvm.viewmodel.MyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleNoteFragment : Fragment() {
    private lateinit var bindingSingleF:FragmentSingleNoteBinding
    private var noteColor = "#64dd17"
    private lateinit var noteEntity: NoteEntity
    //init ViewModel
    private val viewModel : MyViewModel by viewModels()

    var isUpdating = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingSingleF = DataBindingUtil.inflate(inflater , R.layout.fragment_single_note , container , false)
        bindingSingleF.singleNote = this
        getDataFromBundle()
        return bindingSingleF.root
    }
    inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }
    private fun getDataFromBundle() {
        if (arguments != null){
            noteEntity = arguments?.parcelable("selected_note")!!
//            noteEntity = arguments?.getParcelable("selected_note")!!
            isUpdating = true

            bindingSingleF.edtxTitle.setText(noteEntity.title)
            bindingSingleF.edtxContent.setText(noteEntity.content)
            noteColor = noteEntity.color.toString()

            hideAllChecks()
            colorCheck(noteColor)
        }
    }


    fun onClickAddNote(view : View){
        if (isUpdating){
            bindingSingleF.apply {
                if (this.edtxTitle.text.isNullOrBlank())
                    Snackbar.make(this.coordSingleNote , "Please enter your title!" , Snackbar.LENGTH_SHORT).show()
                else {
                    if (this.edtxContent.text.isNullOrBlank())
                        Snackbar.make(this.coordSingleNote , "Please enter your note!" , Snackbar.LENGTH_SHORT).show()
                    else{
                        noteEntity.title = this.edtxTitle.text.toString()
                        noteEntity.content = this.edtxContent.text.toString()
                        noteEntity.color = noteColor
                        noteEntity.pinned = false

                        viewModel.updateNote(noteEntity)
                        view.findNavController().navigate(R.id.action_singleNoteFragment_to_homeFragment)
                        //Or Navigation.findNacController(view).navigate(.....)
                    }
                }
            }
        }
        else{
            bindingSingleF.apply {
                if (this.edtxTitle.text.isNullOrBlank())
                    Snackbar.make(this.coordSingleNote , "Please enter your title!" , Snackbar.LENGTH_SHORT).show()
                else {
                    if (this.edtxContent.text.isNullOrBlank())
                        Snackbar.make(this.coordSingleNote , "Please enter your note!" , Snackbar.LENGTH_SHORT).show()
                    else{
                        val title = this.edtxTitle.text.toString()
                        val content = this.edtxContent.text.toString()
                        val color = noteColor

                        val noteModel = NoteEntity(0 , title , content , color , false)

                        viewModel.insertNoteToDB(noteModel)
                        view.findNavController().navigate(R.id.action_singleNoteFragment_to_homeFragment)
                        //Or Navigation.findNacController(view).navigate(.....)
                    }
                }
            }
        }
    }

    fun onClickColorCheck(check : View){
        hideAllChecks()
        check.visibility = View.VISIBLE
        bindingSingleF.apply {
            when(check.id){
                this.imgvCheck1.id -> noteColor = "#64dd17"
                this.imgvCheck2.id -> noteColor = "#448aff"
                this.imgvCheck3.id -> noteColor = "#ffff00"
                this.imgvCheck4.id -> noteColor = "#bdbdbd"
                this.imgvCheck5.id -> noteColor = "#ff5252"
                this.imgvCheck6.id -> noteColor = "#e040fb"
            }
        }
    }
    private fun hideAllChecks(){
        bindingSingleF.apply {
            this.imgvCheck1.visibility = View.INVISIBLE
            this.imgvCheck2.visibility = View.INVISIBLE
            this.imgvCheck3.visibility = View.INVISIBLE
            this.imgvCheck4.visibility = View.INVISIBLE
            this.imgvCheck5.visibility = View.INVISIBLE
            this.imgvCheck6.visibility = View.INVISIBLE
        }
    }
    private fun colorCheck(color:String){
        bindingSingleF.apply {
            when(color){
                "#64dd17" -> this.imgvCheck1.visibility = View.VISIBLE
                "#448aff" -> this.imgvCheck2.visibility = View.VISIBLE
                "#ffff00" -> this.imgvCheck3.visibility = View.VISIBLE
                "#bdbdbd" -> this.imgvCheck4.visibility = View.VISIBLE
                "#ff5252" -> this.imgvCheck5.visibility = View.VISIBLE
                "#e040fb" -> this.imgvCheck6.visibility = View.VISIBLE
            }
        }
    }
}