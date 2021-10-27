package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentSignUpBinding
import com.example.hotspotalk.view.util.Preference.token
import com.example.hotspotalk.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 회원가입 프래그먼트
 */

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            when (it.token) {
                null -> Toast.makeText(requireContext(), "실패", Toast.LENGTH_SHORT).show()

                else -> {
                    token = it.token!!
                    navigateToHome()
                }
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHome() {
        navController.navigate(R.id.action_signUpFragment_to_homeFragment)
    }
}