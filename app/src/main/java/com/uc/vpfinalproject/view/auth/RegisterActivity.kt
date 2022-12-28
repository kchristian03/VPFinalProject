package com.uc.vpfinalproject.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.uc.vpfinalproject.databinding.ActivityRegisterBinding
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.DataLoginResponse
import com.uc.vpfinalproject.model.DataRegisterResponse
import com.uc.vpfinalproject.view.NavBarActivity
import com.uc.vpfinalproject.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.progressBarRegister.visibility = View.GONE

        val token = SessionManager.fetchAuthToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.registerResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processRegister(it.data)
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

        binding.buttonRegisterGoToLogin.setOnClickListener {
            doLogin()
        }

        binding.buttonRegisterRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    private fun doRegister(){
        val name = binding.TextInputRegisterName.text.toString().trim()
        val email = binding.TextInputRegisterEmail.text.toString().trim()
        val username = binding.TextInputRegisterUsername.text.toString().trim()
        val password = binding.TextInputRegisterPassword.text.toString().trim()
        viewModel.registerUser(name, email, username, password)
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun processRegister(data: DataRegisterResponse?) {
        showToast("" + data?.message)
        if (!data?.data?.token.isNullOrEmpty()) {
            data?.data?.token?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding.progressBarRegister.visibility = View.GONE
        binding.buttonRegisterRegister.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.buttonRegisterRegister.visibility = View.GONE
        binding.progressBarRegister.visibility = View.VISIBLE
    }

    private fun navigateToHome() {
        val intent = Intent(this, NavBarActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }
}