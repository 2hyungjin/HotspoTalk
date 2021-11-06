package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import java.lang.Exception

@AndroidEntryPoint
class ChattingFragment : Fragment() {
    lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingViewModel by viewModels()
    lateinit var chattingListAdapter: MessageListAdapter
    lateinit var userListAdapter: UserListAdapter
    var roomId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChattingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomId = requireArguments().getInt("id", 0)

        try {
            viewModel.enterChatting(roomId ?: throw Exception("방 참가에 오류가 생겼어요"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
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
            val content = binding.editText.text.toString()
            val message = Gson().toJson(
                MessageRequest(
                    "message",
                    roomId!!,
                    content,
                    Preference.token
                )
            )
            HotspotalkApplication.socket.emit(
                "message",
                message
            )
            binding.editText.text.clear()
            Log.d("chatting", message)
            val myMessage = Message(
                "나",
                content,
                roomID = roomId!!,
                messageType = MessageType.MINE
            )
            val newList = ArrayList(chattingListAdapter.currentList)
            newList.add(myMessage)

            chattingListAdapter.submitList(
                newList
            )

        }


        binding.btnOutChattingFragment.setOnClickListener {
            viewModel.outChatting(roomId!!)
            findNavController().navigateUp()
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
        chat.observe(viewLifecycleOwner) {
            if (it.roomID == roomId) {
                chattingListAdapter.submitList(
                    chattingListAdapter.currentList.toMutableList().apply {
                        this.add(it)
                    })
            }
        }
    }
}