package com.uc.vpfinalproject.view.yoga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityExerciseBinding
import com.uc.vpfinalproject.databinding.ActivityRestBinding

class RestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()


        binding.addtimeBTN.setOnClickListener(){
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}