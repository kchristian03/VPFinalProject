package com.uc.vpfinalproject.view.home

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.TextView
import com.uc.vpfinalproject.databinding.ActivityCreateNoteBinding
import com.uc.vpfinalproject.databinding.ActivityMeditateBinding
import com.uc.vpfinalproject.view.NavBarActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.fixedRateTimer

class MeditateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeditateBinding
    private var play = true
    private var watch = Stopwatch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        watch.start()

        animate()
        Listener()
        updateUITextView(watch, binding.timeMeditateTV)
    }

    fun updateUITextView(stopwatch: Stopwatch, textview: TextView) = GlobalScope.launch(Dispatchers.Main) {
        while (true) {
            val elapsedTime = stopwatch.getElapsedTime()
            val minute = (elapsedTime / 1000) / 60
            val seconds = (elapsedTime / 1000) % 60
            textview.text = String.format("%02d:%02d", minute, seconds)
            delay(1000) // update every second
        }
    }

    private fun Listener() {

        val animatorScreen = ObjectAnimator.ofFloat(window.decorView, "alpha", 1f, 0.85f, 0.65f, 0.85f, 1f)
        animatorScreen.duration = 8000
        animatorScreen.repeatCount = ObjectAnimator.INFINITE
        animatorScreen.interpolator = LinearInterpolator()
        animatorScreen.start()

        val fadeInOutAnimator = ValueAnimator.ofFloat(0.7f, 1f)
        fadeInOutAnimator.duration = 4000
        fadeInOutAnimator.repeatMode = ValueAnimator.REVERSE
        fadeInOutAnimator.repeatCount = ValueAnimator.INFINITE
        fadeInOutAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            binding.timeMeditateTV.alpha = value
        }
        fadeInOutAnimator.start()

        val animator = ObjectAnimator.ofPropertyValuesHolder(
            binding.meditateIV,
            PropertyValuesHolder.ofFloat("scaleX", 0.95f, 1.05f),
            PropertyValuesHolder.ofFloat("scaleY", 0.95f, 1.05f)
        )
        animator.duration = 4000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        animator.start()


        binding.meditateIV.setOnClickListener(){
            if(play){
                play = false
                watch.stop()
                fadeInOutAnimator.cancel()
                animator.cancel()
                animatorScreen.cancel()
            }else{
                play = true
                watch.start()
                fadeInOutAnimator.start()
                animator.start()
                animatorScreen.start()
            }
        }

        binding.backbtnFAB.setOnClickListener(){
            val myIntent2 = Intent(this@MeditateActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }
    }

    private fun animate() {

    }

    fun animateScreen() {
        val animatorScreen = ObjectAnimator.ofFloat(window.decorView, "alpha", 0.8f, 0f, 0.5f, 1f)
        animatorScreen.duration = 4000
        animatorScreen.repeatCount = ObjectAnimator.INFINITE
        animatorScreen.interpolator = LinearInterpolator()
        animatorScreen.start()
    }



    class Stopwatch {

        private var startTime: Long = 0L
        private var running = false
        private var stoppedCount = 0L

        //function to start a stopwatch
        fun start(){
            startTime = System.currentTimeMillis()
            running = true
        }

        //function to stop the stopwatch
        fun stop(){
            if (running)
                stoppedCount += System.currentTimeMillis() - startTime
            running = false
        }

        //function to get elapsed time from the stopwatch
        fun getElapsedTime(): Long {
            return if (running){
                System.currentTimeMillis() - startTime + stoppedCount
            } else {
                stoppedCount
            }
        }

        //function to reset the stopwatch
        fun reset(){
            startTime = 0L
            running = false
            stoppedCount = 0L
        }

    }
}