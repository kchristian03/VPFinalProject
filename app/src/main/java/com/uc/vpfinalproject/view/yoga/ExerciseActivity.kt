package com.uc.vpfinalproject.view.yoga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityExerciseBinding
import com.uc.vpfinalproject.databinding.ActivityNavBarBinding
import com.uc.vpfinalproject.view.NavBarActivity

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()


        binding.pauseBTN.setOnClickListener(){
            val intent = Intent(this, RestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}