package com.uc.vpfinalproject.view.yoga

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityExerciseBinding
import com.uc.vpfinalproject.model.Exercise
import com.uc.vpfinalproject.view.NavBarActivity

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private val Program = ArrayList<Exercise>()
    private var curExercise = Exercise("", 0, "", "")
    private var position: Int = 0
    lateinit var countdown_timer: CountDownTimer
    var time = 0
    var isRunning: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        if(intent.hasExtra("position")){
            position = intent.getIntExtra("position", -1)
        }

        if(position == 0){
            Setup()
        }else if(position == -1){
            val myIntent2 = Intent(this@ExerciseActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }else {
            val program = intent.extras!!.getString("lvl")
            if(program.equals("beginner")){
                Beginner()
            }else if(program.equals("intermediate")){
                Intermediate()
            }else{
                Advanced()
            }
            ExerciseNow(position)
        }
        Listener()

    }

    private fun Listener() {
        binding.pauseBTN.setOnClickListener(){
            if(isRunning){
                pauseTimer()
            }else{
                resumeTimer()
            }
        }

        binding.nextBTN.setOnClickListener(){
            val next = findIndex(Program, curExercise) + 1
            countdown_timer.cancel()
            val myIntent = Intent(this@ExerciseActivity.applicationContext, RestActivity::class.java).apply {
                putExtra("lvl", curExercise.difficulty)
                putExtra("position", next)
            }
            myIntent.putExtra("lvl", curExercise.difficulty)
            myIntent.putExtra("position", next)

            startActivity(myIntent)
            finish()
        }

        binding.prevBTN.setOnClickListener(){
            val next = findIndex(Program, curExercise) - 1
            countdown_timer.cancel()
            Log.d("tttt", next.toString())


            if(next == -1){
                val myIntent2 = Intent(this@ExerciseActivity.applicationContext, NavBarActivity::class.java)
                startActivity(myIntent2)
                finish()
            }else{
                val myIntent = Intent(this@ExerciseActivity.applicationContext, RestActivity::class.java).apply {
                    putExtra("lvl", curExercise.difficulty)
                    putExtra("position", next)
                }
                myIntent.putExtra("lvl", curExercise.difficulty)
                myIntent.putExtra("position", next)

                startActivity(myIntent)
                finish()
            }
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
                binding.durationTV.text = String.format("%02d:%02d", minute, seconds)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                val next = findIndex(Program, curExercise) + 1
                if(Program.size == next){
                    Log.d("tttt", "wtf")
                    val myIntent2 = Intent(this@ExerciseActivity.applicationContext, NavBarActivity::class.java)
                    startActivity(myIntent2)
                    finish()
                }


                Log.d("ttttttttttttt", next.toString())
                val myIntent = Intent(this@ExerciseActivity.applicationContext, RestActivity::class.java).apply {
                    putExtra("lvl", curExercise.difficulty)
                    putExtra("position", next)
                }
                myIntent.putExtra("lvl", curExercise.difficulty)
                myIntent.putExtra("position", next)


                startActivity(myIntent)
                finish()
            }
        }.start()

        isRunning = true
    }

    private fun pauseTimer() {
        countdown_timer.cancel()
        isRunning = false
        binding.pauseBTN.text = "RESUME"
    }

    private fun resumeTimer(){
        Timer(time)
        isRunning = true
        binding.pauseBTN.text = "PAUSE"
    }
    

    private fun ExerciseNow(position: Int) {
        curExercise = Program[position]
        val imageId = resources.getIdentifier(curExercise.img, "drawable", applicationContext.packageName)
        binding.poseIV.setImageResource(imageId)

        binding.poseTV.text = curExercise.name
        Timer(curExercise.duration!!)
    }

    private fun Setup() {
        if(intent.getStringExtra("lvl").equals("beginner")){
            Beginner()
        }else if(intent.getStringExtra("lvl").equals("intermediate")){
            Intermediate()
        }else if(intent.getStringExtra("lvl").equals("advanced")){
            Advanced()
        }else{

        }
        ExerciseNow(0)
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