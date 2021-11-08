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
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.databinding.FragmentHomeVpItemEnteredBinding
import com.example.hotspotalk.view.adapter.EnteredChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.EnteredRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteredRoomFragment : Fragment(),
    EnteredChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {
    private val chattingViewModel: ChattingViewModel by activityViewModels()

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentHomeVpItemEnteredBinding

    private val viewModel: EnteredRoomViewModel by viewModels()

    private val enteredChattingAdapter: EnteredChattingRoomRecyclerViewAdapter =
        EnteredChattingRoomRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_vp_item_entered,
            container,
            false
        )
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        chattingObserve()

    }

    private fun init() {
        HotspotalkApplication.connectSocket()

        binding.rvEnterableRoomVpItemHome.adapter = enteredChattingAdapter
        binding.srlEntered.setOnRefreshListener {
            viewModel.getEnteredRooms()
        }
        viewModel.getEnteredRooms()

    }

    private fun observe() = with(viewModel) {
        isSuccessEnteredRooms.observe(viewLifecycleOwner) {
            when (it) {
                null -> {
                    Toast.makeText(requireContext(), "채팅방을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                    roomVis.value = false
                }

                else -> {
                    roomVis.value = it.isNotEmpty()
                    enteredChattingAdapter.submitList(it)

                    it.forEach {
                        HotspotalkApplication.socket.emit("in", it.roomID)
                    }
                }
            }
            binding.srlEntered.isRefreshing = false
        }

        isFailureEnteredRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun chattingObserve() = with(chattingViewModel) {
        chat.observe(requireActivity()) { message ->
            val chatList = enteredChattingAdapter.currentList.toMutableList()
            val target = chatList.find { it.roomID == message.roomID }
            Log.d("entered",target.toString())
            target?.lastChatting = message.content
            chatList.apply {
                remove(target)
                add(0, target)
            }
            enteredChattingAdapter.submitList(chatList)


//            chatList.add(0,target!!)
//            enteredChattingAdapter.setList(chatList)
//            target?.lastChatting = message.content
//            chatList.removeIf { it.roomID == message.roomID }
//            var newRoom:EnteredRoomInfo?=null
//            target?.let { newRoom=EnteredRoomInfo(it.roomID,it.roomName,it.roomPW,it.existPW,it.memberLimit,it.areaType,it.areaDetail,message.content) }
//            chatList.add(0,newRoom!!)
//            enteredChattingAdapter.setList(chatList)

        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getEnteredRooms()
    }


    override fun onClick(room: EnteredRoomInfo) {
        val bundle = Bundle()
        bundle.putString("name", room.roomName)
        bundle.putInt("id", room.roomID)
        navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
    }

}