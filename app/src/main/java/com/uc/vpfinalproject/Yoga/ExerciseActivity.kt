package com.uc.vpfinalproject.Yoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityExerciseBinding
import com.uc.vpfinalproject.databinding.ActivityNavBarBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}