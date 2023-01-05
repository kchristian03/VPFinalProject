package com.uc.vpfinalproject.view.home

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.viewmodel.home.HomeViewModel
import com.uc.vpfinalproject.databinding.FragmentHomeBinding
import com.uc.vpfinalproject.helper.GlobalVar
import com.uc.vpfinalproject.helper.GlobalVar.Companion.lastIncrementDate
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.auth.DataLogoutResponse
import com.uc.vpfinalproject.model.auth.DataUserResponse
import com.uc.vpfinalproject.view.MainActivity
import com.uc.vpfinalproject.view.yoga.OverviewActivity
import com.uc.vpfinalproject.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val token = activity?.let { SessionManager.fetchAuthToken(it) }
        val viewModel by viewModels<AuthViewModel>()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        if (token != null) {
            viewModel.getDataUser(token)
        }


        viewModel.getDataResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
//                    showLoading()
                }

                is BaseResponse.Success -> {
//                    stopLoading()
                    processGetData(it.data)
                }

                is BaseResponse.Error -> {
//                    stopLoading()
                    processError(it.msg)
                }

                else -> {
//                    stopLoading()
                }
            }
        }


        viewModel.logoutResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
//                    showLoading()
                }

                is BaseResponse.Success -> {
//                    stopLoading()
                    processLogout(it.data)
                }

                is BaseResponse.Error -> {
//                    stopLoading()
                    processError(it.msg)
                }

                else -> {
//                    stopLoading()
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            doLogout(viewModel, token)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        Listener()
        animate()

        showGreeting(GlobalVar.userData?.data?.Name)
        return root
    }

    private fun animate() {
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            binding.imageView3,
            PropertyValuesHolder.ofFloat("scaleX", 0.95f, 1.05f),
            PropertyValuesHolder.ofFloat("scaleY", 0.95f, 1.05f)
        )
        animator.duration = 6000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private fun Listener() {
        binding.meditateCV.setOnClickListener(){
            checkstreak()
            val myIntent = Intent(activity, MeditateActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun checkstreak() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val dateValue: Date? = lastIncrementDate?.let { dateFormat.parse(it) }
        //store the latest increment date
        if (lastIncrementDate == null) {
            //increment value, if current day is not same as that of lastIncrementDate
            GlobalVar.currrentUserStreak++
            binding.streakTV.text = GlobalVar.currrentUserStreak.toString() + " Streak"
            //store the latest increment date
            lastIncrementDate = currentDate.toString()

            //post value currentstreak and streakdate ke API disini

        }
        if (dateValue != null) {
            if (dateValue.before(currentDate)) {
                //increment value, if current day is not same as that of lastIncrementDate
                GlobalVar.currrentUserStreak++
                binding.streakTV.text = GlobalVar.currrentUserStreak.toString() + " Streak"
                //store the latest increment date
                lastIncrementDate = currentDate.toString()

                //post value currentstreak and streakdate ke API disini
            }
        }
    }

    private fun processGetData(data: DataUserResponse?) {
//        showToast("" + data?.message)
        if (data != null) {
//            showToast("data not null")
            GlobalVar.userData = data
        }




        if(data?.data?.Streak == null){
            GlobalVar.currrentUserStreak = 0
            binding.streakTV.text = GlobalVar.currrentUserStreak.toString() + " Streak"
        }else{
            GlobalVar.currrentUserStreak = data?.data?.Streak
            binding.streakTV.text = GlobalVar.currrentUserStreak.toString() + " Streak"
        }

        if(data?.data?.Streak != null){
            lastIncrementDate = data.data.StreakDate
        }

//        if (namedata != null) {
//            showGreeting(namedata)
//        }
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun processLogout(data: DataLogoutResponse?) {
//        showToast("" + data?.message)
        activity?.let { it1 -> SessionManager.clearData(it1) }
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun stopLoading() {
        TODO("Not yet implemented")
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }

    private fun showGreeting(name: String?) {
        val sdf = SimpleDateFormat("HH:mm:ss")
        val currentDate = sdf.format(Date())

        if (currentDate >= "00:00:00" && currentDate <= "11:59:59") {
            binding.tvGreeting.text = getString(R.string.morning)
        } else if (currentDate >= "12:00:00" && currentDate <= "17:59:59") {
            binding.tvGreeting.text = getString(R.string.afternoon)
        } else if (currentDate >= "18:00:00" && currentDate <= "23:59:59") {
            binding.tvGreeting.text = getString(R.string.evening)
        }

        binding.tvName.text = name
    }

    private fun doLogout(viewModel: AuthViewModel, token: String?) {
        if (token == null){
            showToast("Token is null")
        } else {
            viewModel.logoutUser(token)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}