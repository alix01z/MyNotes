package com.alix01z.noteappkotlin_mvvm.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alix01z.noteappkotlin_mvvm.R
import com.alix01z.noteappkotlin_mvvm.databinding.PinnedRvItemBinding
import com.alix01z.noteappkotlin_mvvm.room.entities.NoteEntity

class PinnedRvAdapter(private var pinnedNoteList: MutableList<NoteEntity> , private var listener: CardClickListener) : RecyclerView.Adapter<PinnedRvAdapter.PinnedRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedRvViewHolder {
        val pinnedRvItemBinding : PinnedRvItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context) ,
            R.layout.pinned_rv_item , parent , false)
        return PinnedRvViewHolder(pinnedRvItemBinding)
    }

    override fun getItemCount(): Int {
        return pinnedNoteList.size
    }

    override fun onBindViewHolder(holder: PinnedRvViewHolder, position: Int) {
        holder.bindData(pinnedNoteList[position] , listener)

    }

    class PinnedRvViewHolder(private var binding: PinnedRvItemBinding): RecyclerView.ViewHolder(binding.root){


        fun bindData(noteEntity: NoteEntity , listener: CardClickListener) {

            //We used AppCompatTextview to predict causing any problem while scrolling Rv ( We predict we will have so many items)
            binding.txtvItemTitle.setTextFuture(
                noteEntity.title?.let {
                    PrecomputedTextCompat.getTextFuture(
                        it,
                        binding.txtvItemTitle.textMetricsParamsCompat,
                        null
                    )
                }
            )
            binding.cvPinnedItems.setCardBackgroundColor(Color.parseColor(noteEntity.color))
            binding.txtvItemContent.text = noteEntity.content
            binding.cvPinnedItems.setOnClickListener {
                listener.onItemClickListener(noteEntity)
            }
            binding.executePendingBindings()
        }

    }

}