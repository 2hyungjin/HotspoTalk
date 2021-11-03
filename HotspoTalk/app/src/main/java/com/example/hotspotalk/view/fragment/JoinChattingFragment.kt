package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentJoinChattingBinding

class JoinChattingFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentJoinChattingBinding

    private val navArgs by navArgs<JoinChattingFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join_chatting, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseJoinChatting.setOnClickListener {
            navigateToHome()
        }

        binding.btnJoinChatting.setOnClickListener {
            navigateToSetProfile()
        }
    }

    private fun navigateToHome() {
        navController.navigate(R.id.action_joinChattingFragment_to_homeFragment)
    }

    private fun navigateToSetProfile() {
        navController.navigate(JoinChattingFragmentDirections.actionJoinChattingFragmentToSetProfileFragment(navArgs.roomID))
    }
}