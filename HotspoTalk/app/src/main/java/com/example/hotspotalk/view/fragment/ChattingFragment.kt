package com.example.hotspotalk.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.databinding.FragmentChattingBinding
import com.example.hotspotalk.viewmodel.ChattingViewModel

class ChattingFragment : Fragment() {
    lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChattingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.enterChatting(1)

        binding.btnOpenMenu.setOnClickListener {
            binding.constraintLayout2.transitionToEnd()
        }
        binding.btnOutChattingFragment.setOnClickListener {
            //방 나가기
            findNavController().navigateUp()
        }

    }

}