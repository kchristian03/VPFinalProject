package com.uc.vpfinalproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.viewmodel.home.HomeViewModel
import com.uc.vpfinalproject.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var username = ""
    private var streak = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {

        }

        Tempdata()
        Display()
        Listener()

        return root
    }

    private fun Listener() {
        binding.MeditateCV.setOnClickListener(){
            //create meditation activity
        }
    }


    private fun Tempdata() {
        username = "Guest"
        streak = 3
    }

    private fun Display() {
        binding.welcomeTV.text = "Welcome " + username
        binding.streaksTV.text = streak.toString() + " Streak"


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}