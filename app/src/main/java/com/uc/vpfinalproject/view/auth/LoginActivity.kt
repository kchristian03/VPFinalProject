package com.uc.vpfinalproject.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc.vpfinalproject.databinding.ActivityLoginBinding
import com.uc.vpfinalproject.view.NavBarActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.buttonLoginGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLoginLogin.setOnClickListener {
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
        }
    }
}