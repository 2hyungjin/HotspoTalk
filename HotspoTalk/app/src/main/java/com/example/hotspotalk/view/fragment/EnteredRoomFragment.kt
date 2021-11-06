package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeVpItemEnteredBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.EnteredRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteredRoomFragment : Fragment(),
    ChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentHomeVpItemEnteredBinding

    private val viewModel: EnteredRoomViewModel by viewModels()

    private val notEnterableAdapter: ChattingRoomRecyclerViewAdapter =
        ChattingRoomRecyclerViewAdapter(this)

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
    }

    private fun init() {
        binding.rvEnterableRoomVpItemHome.adapter = notEnterableAdapter
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
                    notEnterableAdapter.setList(it)
                }
            }
            binding.srlEntered.isRefreshing = false
        }

        isFailureEnteredRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(room: RoomInfo) {
        val bundle = Bundle()
        bundle.putInt("id", room.roomID)
        navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
    }

}