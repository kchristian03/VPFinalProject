package com.uc.vpfinalproject.view.note

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.uc.vpfinalproject.adapter.LogbookRVAdapter
import com.uc.vpfinalproject.databinding.FragmentNoteBinding
import com.uc.vpfinalproject.helper.GlobalVar
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.note.GetNoteResponse
import com.uc.vpfinalproject.viewmodel.note.NoteViewModel

class NoteFragment : Fragment(), Cardlistener {

    private var _binding: FragmentNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var noteAdaptor: LogbookRVAdapter

    //temp array for rv

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        binding.makeNoteTextView.visibility = View.GONE
        stopLoading()
        val root: View = binding.root
        val viewModel by viewModels<NoteViewModel>()
        val token = activity?.let { SessionManager.fetchAuthToken(it) }




        binding.addNotesBTN.setOnClickListener() {
            val myIntent = Intent(activity, CreateNoteActivity::class.java)
            requireActivity().startActivity(myIntent)
        }

        binding.toolbarNote.title = "Meditation Journal"
        binding.toolbarNote.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


        init(viewModel, token)
        display()

        binding.searchViewNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                noteAdaptor?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                noteAdaptor?.getFilter()?.filter(newText);
                return true
            }

        })

        return root
    }

    private fun init(viewModel: NoteViewModel, token: String?) {
        //get data from API disini
        if (token != null) {
            viewModel.getNote(token)
        }
        viewModel.getNoteResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processGetNote(it.data)
                    if (GlobalVar.listNote.isEmpty()) {
                        binding.makeNoteTextView.visibility = View.VISIBLE
                    } else {
                        binding.makeNoteTextView.visibility = View.GONE
                    }
                }
                is BaseResponse.Error -> {
                    stopLoading()
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }

    private fun display() {
//        val adapter = GlobalVar.listNote?.let { LogbookRVAdapter(it, this) }
//        val adapter =LogbookRVAdapter(GlobalVar.listNote, this)
        noteAdaptor = LogbookRVAdapter(GlobalVar.listNote, this)
        binding.notesRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRV.adapter = noteAdaptor
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        binding.notesRV.layoutManager = layoutManager
//        binding.notesRV.adapter = adapter
    }

    private fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun processGetNote(data: GetNoteResponse?) {
        GlobalVar.listNote.clear()
        if (data != null) {
            showToast(data.message)
            GlobalVar.listNote.addAll(data.data)
        }

    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun stopLoading() {
        binding.progressBarNote.visibility = View.GONE
//        binding.buttonLoginLogin.visibility = View.VISIBLE
    }

    private fun showLoading() {
//        binding.buttonLoginLogin.visibility = View.GONE
        binding.progressBarNote.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        LogbookRVAdapter(GlobalVar.listNote, this).notifyDataSetChanged()
    }

    override fun onCardClick(position: Int, id: Int) {

        val myIntent = Intent(activity, CreateNoteActivity::class.java).apply {
            putExtra("position", position); putExtra("id", id)
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