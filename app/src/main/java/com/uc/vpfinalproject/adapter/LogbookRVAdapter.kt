package com.uc.vpfinalproject.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.uc.vpfinalproject.model.Note
import com.uc.vpfinalproject.view.note.Cardlistener
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.CardNoteBinding
import com.uc.vpfinalproject.model.note.Data
import com.uc.vpfinalproject.model.note.DataNote

class LogbookRVAdapter(val listNote: ArrayList<Data>, val cardListener: Cardlistener) :
    RecyclerView.Adapter<LogbookRVAdapter.viewHolder>() {

    class viewHolder (val itemview: View, val cardListener1: Cardlistener): RecyclerView.ViewHolder(itemview){

        val binding = CardNoteBinding.bind(itemview)

        fun setData(data: Data){
            binding.noteJudulTV.text = data.Title
//            data.Title.also { binding.noteJudulTV.text = it }
            if (data.Content.length <= 40) {
                binding.noteIsiTV.text = data.Content
            } else {
                binding.noteIsiTV.text = data.Content.substring(0, 40) + "    ..."
            }

            itemview.setOnClickListener{
                cardListener1.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_note, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listNote[position])
    }

    override fun getItemCount(): Int {
        return listNote.size
    }


}