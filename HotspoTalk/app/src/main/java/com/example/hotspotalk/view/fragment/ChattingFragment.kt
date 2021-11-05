package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import com.example.hotspotalk.data.entity.request.MessageRequest
import com.example.hotspotalk.databinding.FragmentChattingBinding
import com.example.hotspotalk.view.adapter.MessageListAdapter
import com.example.hotspotalk.view.adapter.UserListAdapter
import com.example.hotspotalk.view.util.Preference
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.google.gson.Gson
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
        val roomId: Int = requireArguments().getInt("id")


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
        binding.btnExitChatting.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.viewOffsetChattingFragment.setOnClickListener {
            binding.constraintLayout2.transitionToStart()
        }

        binding.btnSendChattingChattingFragment.setOnClickListener {
            HotspotalkApplication.socket.emit(
                "message",
                Gson().toJson(
                    MessageRequest(
                        "message",
                        roomId,
                        binding.editText.text.toString(),
                        Preference.token
                    )
                )
            )
        }
        binding.btnOutChattingFragment.setOnClickListener {
            //out chatting
        }

        observe()

    }

    private fun observe() = with(viewModel) {
        chatList.observe(viewLifecycleOwner) {
            chattingListAdapter.submitList(it)
        }
        memberList.observe(viewLifecycleOwner) {
            userListAdapter.submitList(it)
        }
    }
}