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
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.databinding.FragmentHomeVpItemEnteredBinding
import com.example.hotspotalk.view.adapter.EnteredChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.view.util.EventObserver
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.EnteredRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteredRoomFragment : Fragment(),
    EnteredChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {
    private val chattingViewModel: ChattingViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentHomeVpItemEnteredBinding

    private val viewModel: EnteredRoomViewModel by activityViewModels()

    private val enteredChattingAdapter: EnteredChattingRoomRecyclerViewAdapter =
        EnteredChattingRoomRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelStore.clear()
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

        binding.rvEnterableRoomVpItemHome.adapter = enteredChattingAdapter
        binding.srlEntered.setOnRefreshListener {
            viewModel.getEnteredRooms()
        }
        viewModel.getEnteredRooms()
    }

    private fun observe() = with(viewModel) {
        isSuccessEnteredRooms.observe(viewLifecycleOwner, EventObserver {
            roomVis.value = it.isNotEmpty()
            enteredChattingAdapter.submitList(it)
            it.forEach {
                HotspotalkApplication.socket.emit("in", it.roomID)
            }
            binding.srlEntered.isRefreshing = false
        })

        isFailureEnteredRooms.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun chattingObserve() = with(chattingViewModel) {
        chattingViewModel.socketInit()
        chat.observe(viewLifecycleOwner) { message ->
            viewModel.getEnteredRooms()
        }
    }


    override fun onClick(room: EnteredRoomInfo) {
        val bundle = Bundle()
        bundle.putString("name", room.roomName)
        bundle.putInt("id", room.roomID)
        navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
    }

}