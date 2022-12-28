package com.uc.vpfinalproject.view.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uc.vpfinalproject.databinding.ActivityLoginBinding
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.DataLoginResponse
import com.uc.vpfinalproject.view.NavBarActivity
import com.uc.vpfinalproject.viewmodel.AuthViewModel
import okhttp3.ResponseBody

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.progressBarLogin.visibility = View.GONE

        val token = SessionManager.fetchAuthToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
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

        binding.buttonLoginGoToRegister.setOnClickListener {
            doRegister()
        }

        binding.buttonLoginLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val username = binding.TextInputLoginUsername.text.toString().trim()
        val password = binding.TextInputLoginPassword.text.toString().trim()
        viewModel.loginUser(username = username, password = password)
    }

    private fun doRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun processLogin(data: DataLoginResponse?) {
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
        binding.progressBarLogin.visibility = View.GONE
        binding.buttonLoginLogin.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.buttonLoginLogin.visibility = View.GONE
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun navigateToHome() {
        val intent = Intent(this, NavBarActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }
}
