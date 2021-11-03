package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentLoginBinding
import com.example.hotspotalk.view.util.Preference.token
import com.example.hotspotalk.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 로그인 프래그먼트
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
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
            // todo 토큰 저장
            when (it) {
                null ->
                    Toast.makeText(requireContext(), "아이디 또는 비밀번호가 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                else ->
                    token = it.token!!
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            Log.d("login", it)
            Toast.makeText(requireContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}