package com.alix01z.noteappkotlin_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alix01z.noteappkotlin_mvvm.R
import com.alix01z.noteappkotlin_mvvm.adapters.CardClickListener
import com.alix01z.noteappkotlin_mvvm.adapters.PinnedRvAdapter
import com.alix01z.noteappkotlin_mvvm.databinding.FragmentHomeBinding
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity
import com.alix01z.noteappkotlin_mvvm.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() , CardClickListener {
    //ViewBinding
    private lateinit var bingdingHomeF:FragmentHomeBinding

    //ViewModel
    private val myViewModel:MyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bingdingHomeF = DataBindingUtil.inflate(inflater , R.layout.fragment_home , container , false)
        bingdingHomeF.fragmentHome = this
        initPinnedRecyclerView()
        return bingdingHomeF.root
    }

    private fun initPinnedRecyclerView(){
        myViewModel.myLiveData.observe(viewLifecycleOwner) { list ->
            val data: ArrayList<NoteEntity> = ArrayList()
            list.forEach {
                if (!it.pinned) {
                    data.add(it)
                }
            }
            //Check
            if (data.isEmpty())
                bingdingHomeF.txtvsEmptyNotes.visibility = View.VISIBLE
            else
                bingdingHomeF.txtvsEmptyNotes.visibility = View.GONE

            //init RecyclerView
            bingdingHomeF.rvPinnedNotes.adapter = PinnedRvAdapter(data , this)
            bingdingHomeF.rvPinnedNotes.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
    //init FloatingActionButton Listener
    fun fabOnClick(view : View){
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNoteFragment)
    }

    override fun onItemClickListener(noteEntity: NoteEntity) {
        val bundleSendData = bundleOf("selected_note" to noteEntity)
        Navigation.findNavController(bingdingHomeF.root).navigate(R.id.action_homeFragment_to_singleNoteFragment  , bundleSendData)
    }

    override fun onOptionClickListener(imgvItemOption: View, noteEntity: NoteEntity) {
        val popupMenu = PopupMenu(requireActivity() , imgvItemOption)
        popupMenu.inflate(R.menu.item_option_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_menu_item -> {
                    myViewModel.deleteNote(noteEntity)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}