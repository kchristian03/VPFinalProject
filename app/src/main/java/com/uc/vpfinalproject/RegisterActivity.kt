package com.uc.vpfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc.vpfinalproject.databinding.ActivityLoginBinding
import com.uc.vpfinalproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}