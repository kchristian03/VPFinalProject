package com.uc.vpfinalproject.view.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.uc.vpfinalproject.adapter.LogbookRVAdapter
import com.uc.vpfinalproject.model.Note
import com.uc.vpfinalproject.databinding.FragmentNoteBinding

class NoteFragment : Fragment(), Cardlistener {

    private var _binding: FragmentNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //temp array for rv
    private val temp = ArrayList<Note>()
    private val mboh = Note("test", "test")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.addNotesBTN.setOnClickListener(){
            val myIntent = Intent(activity, CreateNoteActivity::class.java)
            requireActivity().startActivity(myIntent)
        }

        Display()

        return root
    }

    private fun init() {

    }

    private fun Display() {
        temp.add(mboh)
        val adapter = LogbookRVAdapter(temp, this)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRV.layoutManager = layoutManager
        binding.notesRV.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        temp.add(mboh)
        val adapter = LogbookRVAdapter(temp, this)
        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(position: Int) {

        val myIntent = Intent(activity, CreateNoteActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }

    override fun onCardClicked(view: View, position: Int) {

        val myIntent = Intent(activity, CreateNoteActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }
}