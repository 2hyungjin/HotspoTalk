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
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ChattingFragment : Fragment() {
    lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingViewModel by viewModels()
    private lateinit var chattingListAdapter: MessageListAdapter
    private lateinit var userListAdapter: UserListAdapter

    var roomId: Int? = null
    lateinit var roomName: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelStore.clear()
        binding = FragmentChattingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomId = requireArguments().getInt("id", 0)
        roomName = requireArguments().getString("name", "채팅")

        Log.d("TAG", "onViewCreated: ")

        chattingListAdapter = MessageListAdapter()
        userListAdapter = UserListAdapter()


        try {
            viewModel.enterChatting(roomId ?: throw Exception("방 참가에 오류가 생겼어요"))
            viewModel.clearList()
            chattingListAdapter.clearList()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        initBinding()
        observe()
        viewModel.socketInit()
        HotspotalkApplication.socket.emit("in", roomId)
    }

    private fun initBinding() {
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
            postChat(content)

            scrollRv()
        }
        binding.btnOutChattingFragment.setOnClickListener {
            viewModel.outChatting(roomId!!)
            findNavController().navigateUp()
        }
        binding.tvTitleChattingFragment.text = roomName
    }

    private fun postChat(content: String) {
        if (content.isBlank()) return

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
        addMyChatting(content)
    }

    private fun addMyChatting(content: String) {
        val myMessage = Message(
            "나",
            content,
            roomID = roomId!!,
            timestamp = SimpleDateFormat("hh:mm")
                .format(Date(System.currentTimeMillis())),
            messageType = MessageType.MINE
        )
        chattingListAdapter.addMessage(myMessage)

    }


    private fun observe() = with(viewModel) {
        chatList.observe(viewLifecycleOwner) {
            chattingListAdapter.addAllMessage(it)
            scrollRv()
        }
        memberList.observe(viewLifecycleOwner) {
            binding.tvUserCountChattingFragment.text = "${userListAdapter.itemCount}명"
            userListAdapter.submitList(it)
        }
        chat.observe(viewLifecycleOwner) {
            Log.d("TAG", "observe: $it")
            if (it.messageType == MessageType.BREAK) {
                findNavController().navigateUp()
            }
            if (it.messageType == MessageType.COMMAND) {
                viewModel.clearMemberList()
                viewModel.getMembers(roomId!!)
            }
            if (chatList.value?.contains(it) == false && it.roomID == this@ChattingFragment.roomId) {
                chattingListAdapter.addMessage(it)
                scrollRv()
            }
        }
    }

    private fun scrollRv() {
        binding.rvChattingChattingFragment.scrollToPosition(chattingListAdapter.itemCount - 1)
    }
}