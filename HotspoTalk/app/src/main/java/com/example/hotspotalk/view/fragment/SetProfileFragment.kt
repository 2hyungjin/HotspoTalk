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


    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoinChattingSetProfileBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        val roomID = requireArguments().getInt("roomID", 0)
        val existPW = requireArguments().getBoolean("existPW", false)

        binding.etPasswordJoinChattingSetProfile.visibility =
            if (existPW) {
                View.VISIBLE
            } else {
                View.GONE
            }

        binding.btnConfirmSetProfile.setOnClickListener {
            viewModel.putJoinChatting(roomID)
        }

        binding.btnCloseSetProfile.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun observe() = with(viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            navController.navigateUp()
        }

        isFailure.observe(viewLifecycleOwner) {
            Log.d("joinChatting", it)
            Toast.makeText(requireContext(), "이미 참여 중인 채팅방입니다.", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }
    }
}