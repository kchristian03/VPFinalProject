package com.uc.vpfinalproject.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.auth.DataPingResponse
import com.uc.vpfinalproject.view.auth.LoginActivity
import com.uc.vpfinalproject.viewmodel.AuthViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<AuthViewModel>()
    private var ping = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()


        viewModel.pingServer()


        viewModel.pingResult.observe(this@MainActivity) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processPing(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }


    }

    private fun processError(msg: String?) {
        showToast("null")
    }

    private fun processPing(data: DataPingResponse?) {
        ping = true
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}