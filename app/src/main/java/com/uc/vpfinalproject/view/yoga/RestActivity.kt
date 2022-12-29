package com.uc.vpfinalproject.view.yoga

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uc.vpfinalproject.databinding.ActivityRestBinding
import com.uc.vpfinalproject.model.Exercise
import com.uc.vpfinalproject.view.NavBarActivity

class RestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestBinding
    private var Program = ArrayList<Exercise>()
    private var curExercise = Exercise("", 0, "", "")
    private var Position: Int = -2
    var time = 0
    lateinit var countdown_timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        Setup()

        Listener()
    }

    private fun Listener() {
        binding.addtimeBTN.setOnClickListener(){
            countdown_timer.cancel()
            Timer(time+10)
        }

        binding.nextBTN.setOnClickListener(){
            val next = findIndex(Program, curExercise)
            countdown_timer.cancel()
            val myIntent = Intent(this@RestActivity.applicationContext, ExerciseActivity::class.java).apply {
                putExtra("lvl", curExercise.difficulty)
                putExtra("position", next)
            }
            myIntent.putExtra("lvl", curExercise.difficulty)
            myIntent.putExtra("position", next)

            startActivity(myIntent)
            finish()
        }

    }

    private fun Timer(duration: Int) {
        var milis = duration * 1000
        countdown_timer = object : CountDownTimer(milis.toLong(), 1000) {

            // Callback function, fired on regular interval
            override fun onTick(milis: Long) {
                time = (milis / 1000).toInt()
                val minute = (milis / 1000) / 60
                val seconds = (milis / 1000) % 60
                binding.resttimeTV.text = String.format("%02d:%02d", minute, seconds)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                val next = findIndex(Program, curExercise)
                val myIntent = Intent(this@RestActivity.applicationContext, ExerciseActivity::class.java).apply {
                    putExtra("lvl", curExercise.difficulty)
                    putExtra("position", next)
                }
                myIntent.putExtra("lvl", curExercise.difficulty)
                myIntent.putExtra("position", next)
                countdown_timer.cancel()
                startActivity(myIntent)
                finish()
            }
        }.start()
    }

    private fun ExerciseNow(position: Int) {
        var program = intent.getStringExtra("lvl")
        if(program.equals("beginner")){
            Beginner()
        }else if(program.equals("intermediate")){
            Intermediate()
        }else{
            Advanced()
        }

        if(Program.size <= position){
            //test this habis ini
            Log.d("tttt", "1")
            val myIntent2 = Intent(this@RestActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }else if(position == -1){
            Log.d("tttt", "2")
            val myIntent2 = Intent(this@RestActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }else{
            curExercise = Program[position]
        }
        val imageId = resources.getIdentifier(curExercise.img, "drawable", applicationContext.packageName)
        binding.poserestIV.setImageResource(imageId)
        binding.nameTV.text = curExercise.name

        val minute = curExercise.duration!! / 60
        val seconds = curExercise.duration!! % 60
        binding.timeTV.text = String.format("%02d:%02d", minute, seconds)
        binding.programmeTV.text = curExercise.difficulty!!.capitalize()
        var step = (position + 1).toString()
        binding.stepTV.text = step + "/" + (Program.size + 1).toString()

        Timer(30)
    }

    private fun Advanced() {
        var exercise1 = Exercise("Dog Pose", 60, "dog", "advanced")
        var exercise2 = Exercise("Tree Pose", 45, "tree", "advanced")
        var exercise3 = Exercise("Lotus Pose", 60, "lotus", "advanced")
        var exercise4 = Exercise("Cobra Pose", 30, "cobra", "advanced")
        var exercise5 = Exercise("Warrior Pose", 60, "warrior", "advanced")
        var exercise6 = Exercise("Mountain Pose", 30, "mountain", "advanced")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
        Program.add(exercise5)
        Program.add(exercise6)
    }

    private fun Intermediate() {
        var exercise1 = Exercise("Dog Pose", 60, "dog", "intermediate")
        var exercise2 = Exercise("Tree Pose", 45, "tree", "intermediate")
        var exercise3 = Exercise("Lotus Pose", 60, "lotus", "intermediate")
        var exercise4 = Exercise("Cobra Pose", 30, "cobra", "intermediate")
        var exercise5 = Exercise("Warrior Pose", 60, "warrior", "intermediate")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
        Program.add(exercise5)
    }

    private fun Beginner() {
        var exercise1 = Exercise("Dog Pose", 60, "dog", "beginner")
        var exercise2 = Exercise("Tree Pose", 45, "tree", "beginner")
        var exercise3 = Exercise("Lotus Pose", 60, "lotus", "beginner")
        var exercise4 = Exercise("Cobra Pose", 30, "warrior", "beginner")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
    }

    private fun Setup() {
        Position = intent.getIntExtra("position", -1)
        Log.d("ttttttttttttt", Position.toString())
        if(Position < 0){
            finish()
        }
        ExerciseNow(Position)
    }

    fun findIndex(arr: ArrayList<Exercise>, item: Exercise): Int {
        if(arr.indexOf(item) == arr.size){
            return -1
        }
        return arr.indexOf(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        countdown_timer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdown_timer.cancel()
    }

}