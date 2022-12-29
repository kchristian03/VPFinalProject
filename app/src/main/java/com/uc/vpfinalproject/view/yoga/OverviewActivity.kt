package com.uc.vpfinalproject.view.yoga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.adapter.ExerciseRVAdapter
import com.uc.vpfinalproject.adapter.LogbookRVAdapter
import com.uc.vpfinalproject.databinding.ActivityOverviewBinding
import com.uc.vpfinalproject.databinding.ActivityRestBinding
import com.uc.vpfinalproject.model.Exercise
import com.uc.vpfinalproject.view.NavBarActivity

class OverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverviewBinding
    private var Program = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        Setup()
        Listener()


    }

    private fun Listener() {
        val level = intent.extras!!.getString("lvl")
        if(level.equals("beginner")){
            binding.startBTN.setOnClickListener(){
                val myIntent = Intent(this.applicationContext, ExerciseActivity::class.java).apply {
                    putExtra("lvl", "beginner")
                }
                startActivity(myIntent)
                finish()
            }
        }else if(level.equals("intermediate")){
            binding.startBTN.setOnClickListener(){
                val myIntent = Intent(this.applicationContext, ExerciseActivity::class.java).apply {
                    putExtra("lvl", "intermediate")
                }
                startActivity(myIntent)
                finish()
            }
        }else{
            binding.startBTN.setOnClickListener(){
                val myIntent = Intent(this.applicationContext, ExerciseActivity::class.java).apply {
                    putExtra("lvl", "advanced")
                }
                startActivity(myIntent)
                finish()
            }
        }
        binding.backFAB.setOnClickListener(){
            val myIntent2 = Intent(this@OverviewActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }
    }

    private fun Setup() {
        if(intent.getStringExtra("lvl").equals("beginner")){
            Beginner()
        }else if(intent.getStringExtra("lvl").equals("intermediate")){
            Intermediate()
        }else if(intent.getStringExtra("lvl").equals("advanced")){
            Advanced()
        }else{
            val myIntent2 = Intent(this@OverviewActivity.applicationContext, NavBarActivity::class.java)
            startActivity(myIntent2)
            finish()
        }
        Display()
    }

    private fun Display() {
        binding.lvlTV.text = intent.getStringExtra("lvl")!!.capitalize()
        val adapter = ExerciseRVAdapter(Program)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        binding.OverviewRV.layoutManager = layoutManager
        binding.OverviewRV.adapter = adapter
    }

    private fun Advanced() {
        var exercise1 = Exercise("Dog Pose", 60, "", "advanced")
        var exercise2 = Exercise("Tree Pose", 45, "", "advanced")
        var exercise3 = Exercise("Lotus Pose", 60, "", "advanced")
        var exercise4 = Exercise("Cobra Pose", 30, "", "advanced")
        var exercise5 = Exercise("Warrior Pose", 60, "", "advanced")
        var exercise6 = Exercise("Mountain Pose", 30, "", "advanced")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
        Program.add(exercise5)
        Program.add(exercise6)
    }

    private fun Intermediate() {
        var exercise1 = Exercise("Dog Pose", 60, "", "intermediate")
        var exercise2 = Exercise("Tree Pose", 45, "", "intermediate")
        var exercise3 = Exercise("Lotus Pose", 60, "", "intermediate")
        var exercise4 = Exercise("Cobra Pose", 30, "", "intermediate")
        var exercise5 = Exercise("Warrior Pose", 60, "", "advanced")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
        Program.add(exercise5)
    }

    private fun Beginner() {
        var exercise1 = Exercise("Dog Pose", 60, "", "beginner")
        var exercise2 = Exercise("Tree Pose", 45, "", "beginner")
        var exercise3 = Exercise("Lotus Pose", 60, "", "beginner")
        var exercise4 = Exercise("Cobra Pose", 30, "", "beginner")
        Program.add(exercise1)
        Program.add(exercise2)
        Program.add(exercise3)
        Program.add(exercise4)
    }
}