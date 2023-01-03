package com.uc.vpfinalproject.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.uc.vpfinalproject.R
import com.uc.vpfinalproject.viewmodel.home.HomeViewModel
import com.uc.vpfinalproject.databinding.FragmentHomeBinding
import com.uc.vpfinalproject.helper.SessionManager
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.auth.DataLogoutResponse
import com.uc.vpfinalproject.model.auth.DataUserResponse
import com.uc.vpfinalproject.view.MainActivity
import com.uc.vpfinalproject.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.Date

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

//        val nama = viewModel.getDataUser(token).

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
        return root
    }

    private fun processGetData(data: DataUserResponse?) {
        showToast("" + data?.message)
        val namedata = data?.data?.Name
        if (namedata != null) {
            showGreeting(namedata)
        }
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

    private fun showGreeting(name: String){
        val sdf = SimpleDateFormat("HH:mm:ss")
        val currentDate = sdf.format(Date())

        if (currentDate >= "00:00:00" && currentDate <= "11:59:59") {
            binding.tvGreeting.text = getString(R.string.morning)
        } else if (currentDate >= "12:00:00" && currentDate <= "17:59:59") {
            binding.tvGreeting.text = getString(R.string.afternoon)
        } else if (currentDate >= "18:00:00" && currentDate <= "23:59:59") {
            binding.tvGreeting.text = getString(R.string.evening)
        }

        val nama = "Patrick (Percobaan blm auto)"

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