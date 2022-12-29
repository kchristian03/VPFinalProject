package com.uc.vpfinalproject.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.databinding.ActivityNavBarBinding
import com.uc.vpfinalproject.helper.GlobalVar
import com.uc.vpfinalproject.model.Note

class NavBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        supportActionBar!!.hide()

        //temp data
        val mboh = Note("test", "test", "")
        GlobalVar.listLogs.add(mboh)

        //save data note ke testNotes disini
        //code



        val navController = findNavController(R.id.nav_host_fragment_activity_nav_bar)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_yoga, R.id.navigation_yoga
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}