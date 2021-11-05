package com.example.hotspotalk.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import com.example.hotspotalk.databinding.FragmentChattingBinding
import com.example.hotspotalk.view.adapter.MessageListAdapter
import com.example.hotspotalk.view.adapter.UserListAdapter
import com.example.hotspotalk.viewmodel.ChattingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChattingFragment : Fragment() {
    lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingViewModel by viewModels()
    lateinit var chattingListAdapter: MessageListAdapter
    lateinit var userListAdapter: UserListAdapter
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
        chattingListAdapter = MessageListAdapter()
        userListAdapter = UserListAdapter()

        binding.rvChattingChattingFragment.apply {
            adapter = chattingListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.rvUserListChattingFragment.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.btnOpenMenu.setOnClickListener {
            binding.constraintLayout2.transitionToEnd()
        }
        binding.btnOutChattingFragment.setOnClickListener {
            findNavController().navigateUp()
        }

        observe()

        viewModel.testNewMessage(Message("", "123", 123, "", "", MessageType.MINE, 123))
    }

    private fun observe() = with(viewModel) {
        chatList.observe(viewLifecycleOwner) {
            chattingListAdapter.submitList(it)
        }
    }
}