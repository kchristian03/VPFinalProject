package com.uc.vpfinalproject.view.note

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uc.vpfinalproject.databinding.ActivityCreateNoteBinding
import com.uc.vpfinalproject.helper.GlobalVar
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.Note
import com.uc.vpfinalproject.model.note.CreateNoteResponse
import com.uc.vpfinalproject.model.note.Data
import com.uc.vpfinalproject.model.note.EditNoteResponse
import com.uc.vpfinalproject.view.NavBarActivity
import com.uc.vpfinalproject.viewmodel.note.NoteViewModel
import java.util.*

class CreateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNoteBinding
    private var position = -1
    private val viewModel by viewModels<NoteViewModel>()
//    private val token = SessionManager.fetchAuthToken(this)
//    private val uid = GlobalVar.userData?.data?.ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        @Suppress("DEPRECATION") window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding.progressBarcreatenote.visibility = View.GONE
        binding.deletenoteFAB.visibility = View.GONE
        GetIntent()

        if (position != -1) {
            binding.deletenoteFAB.visibility = View.VISIBLE
        }
        Listener()

        viewModel.createNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processCreateNote(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }

        viewModel.editNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processEditNote(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun processCreateNote(data: CreateNoteResponse?) {
        if (data != null) {
            showToast(data.message)
        }
    }

    private fun processEditNote(data: EditNoteResponse?) {
        if (data != null) {
            showToast(data.message)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun Listener() {
        binding.createNoteFAB.setOnClickListener() {
            val title = binding.createnoteTitle.text.toString().trim()
            val content = binding.createnoteContent.text.toString().trim()
            val token = SessionManager.fetchAuthToken(this)
            val uid = GlobalVar.userData?.data?.ID
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(
                    this, "Please Fill in both the Title and the Contents.", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBarcreatenote.visibility = View.VISIBLE
                if (position != -1) {

                    //yang ini edit data note
//                    edittemp()


                    binding.progressBarcreatenote.visibility = View.GONE
                    startActivity(Intent(this, NavBarActivity::class.java))
                    finish()
                } else {
//                    val note = Note(UUID.randomUUID().toString() , title, content, "ini uid user")
                    //yang ini create data note
//                    createtemp(note)
                    if (token != null && uid != null) {
                        viewModel.createNote(token, title, content, uid)
                    }
                    binding.progressBarcreatenote.visibility = View.GONE
                    startActivity(Intent(this, NavBarActivity::class.java))
                    finish()
                }

            }
        }
        binding.deletenoteFAB.setOnClickListener() {
            //delete data note
            GlobalVar.listLogs.removeAt(position)

            startActivity(Intent(this, NavBarActivity::class.java))
            finish()
        }
    }

    private fun createtemp(note: Note) {
        GlobalVar.listLogs.add(note)
    }

    private fun edittemp() {
        var title = binding.createnoteTitle.text.toString().trim()
        var noteIsi = binding.createnoteContent.text.toString().trim()
        var note = GlobalVar.listLogs[position]
        note.title = title
        note.content = noteIsi
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val note = GlobalVar.listNote[position]
            display(note)
        }
    }

    private fun display(note: Data) {
        binding.createnoteTitle.setText(note.Title)
        binding.createnoteContent.setText(note.Content)

    }

}