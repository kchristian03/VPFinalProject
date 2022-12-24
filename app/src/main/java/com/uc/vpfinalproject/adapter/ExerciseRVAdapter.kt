package com.uc.vpfinalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.CardNoteBinding
import com.uc.vpfinalproject.databinding.ExerciseItemBinding
import com.uc.vpfinalproject.model.Exercise

class ExerciseRVAdapter(val listExercise: ArrayList<Exercise>) :
    RecyclerView.Adapter<ExerciseRVAdapter.viewHolder>() {

    class viewHolder (val itemview: View): RecyclerView.ViewHolder(itemview){

        val binding = ExerciseItemBinding.bind(itemview)

        fun setData(data: Exercise){
            binding.nameExeTV.text = data.name

            val minute = data.duration!! / 60
            val seconds = data.duration!! % 60
            binding.durationExeTV.text = String.format("%02d:%02d", minute, seconds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.exercise_item, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listExercise[position])
    }

    override fun getItemCount(): Int {
        return listExercise.size
    }


}