package com.uc.vpfinalproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.view.auth.LoginActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportActionBar!!.hide()

        Handler().postDelayed({
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}