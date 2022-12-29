package com.uc.vpfinalproject.view.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityCreateNoteBinding
import com.uc.vpfinalproject.helper.GlobalVar
import com.uc.vpfinalproject.model.Note
import com.uc.vpfinalproject.view.NavBarActivity
import java.util.*

class CreateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNoteBinding
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding.progressBarcreatenote.visibility = View.GONE
        binding.deletenoteFAB.visibility = View.GONE
        GetIntent()
        if(position != -1){
            binding.deletenoteFAB.visibility = View.VISIBLE
        }
        Listener()
    }

    private fun Listener() {
        binding.createNoteFAB.setOnClickListener(){
            var title = binding.createnoteTitle.text.toString().trim()
            var noteIsi = binding.createnoteContent.text.toString().trim()
            if(title.isEmpty() || noteIsi.isEmpty()){
                Toast.makeText(this, "Please Fill in both the Title and the Contents.", Toast.LENGTH_SHORT)
            }else{
                binding.progressBarcreatenote.visibility = View.VISIBLE
                if(position != -1){
                    var note = GlobalVar.listLogs[position]
                    note.title = title
                    note.notes = noteIsi
                    //yang ini edit data note


                    binding.progressBarcreatenote.visibility = View.GONE
                    startActivity(Intent(this, NavBarActivity::class.java))
                    finish()
                }else{
                    val note = Note(title, noteIsi, "ini uid user")
                    //yang ini create data note


                    binding.progressBarcreatenote.visibility = View.GONE
                    startActivity(Intent(this, NavBarActivity::class.java))
                    finish()
                }

            }
        }
        binding.deletenoteFAB.setOnClickListener(){
            //delete data note

            startActivity(Intent(this, NavBarActivity::class.java))
            finish()
        }
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val movie = GlobalVar.listLogs[position]
            Display(movie)
        }
    }

    private fun Display(note: Note) {
        binding.createnoteTitle.setText(note.title)
        binding.createnoteContent.setText(note.notes)

    }

}