package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.databinding.FragmentLoginBinding
import com.example.hotspotalk.view.util.Preference
import com.example.hotspotalk.view.util.Preference.token
import com.example.hotspotalk.viewmodel.LoginViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

/**
 * 로그인 프래그먼트
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val permissionLauncher: ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) {
        if (!it.all { permission -> permission.value })
            Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
    }
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelStore.clear()
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (token.isNotEmpty()) {
            viewModel.autoLogin()
        }

        init()
        observe()
        listener()
    }

    private fun init() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            viewModel.devToken = it
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    private fun listener() {
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            when (it) {
                null ->
                    Toast.makeText(requireContext(), "아이디 또는 비밀번호가 옳지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                else -> {
                    if (it is Token) token = it.token
                    Log.d("LoginFragment", "observe: $token")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            Log.d("login", it)
            Toast.makeText(requireContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}