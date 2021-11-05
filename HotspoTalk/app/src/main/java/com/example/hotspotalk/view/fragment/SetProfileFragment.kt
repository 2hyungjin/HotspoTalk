package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentJoinChattingSetProfileBinding
import com.example.hotspotalk.viewmodel.JoinChattingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetProfileFragment : Fragment() {

    private val viewModel: JoinChattingViewModel by viewModels()
    private lateinit var binding: FragmentJoinChattingSetProfileBinding

    private val navArgs by navArgs<SetProfileFragmentArgs>()

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinChattingSetProfileBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.btnConfirmSetProfile.setOnClickListener {
            viewModel.putJoinChatting(navArgs.roomID)
        }

        binding.btnCloseSetProfile.setOnClickListener {
            navController.navigate(R.id.action_setProfileFragment_to_joinChattingFragment)
        }
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            when (it) {
                // todo
                "success" -> ""
                "fail" ->
                    Toast.makeText(requireContext(), "채팅방 참가에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            Log.d("joinChatting", it)
            Toast.makeText(requireContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}