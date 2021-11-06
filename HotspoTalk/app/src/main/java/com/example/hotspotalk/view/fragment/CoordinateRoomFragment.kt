package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeVpItemCoordinateBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.CoordinateRoomViewModel
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoordinateRoomFragment : Fragment(),
    ChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeVpItemCoordinateBinding

    private val viewModel: CoordinateRoomViewModel by viewModels()
    private val chattingViewModel: ChattingViewModel by activityViewModels()

    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.all { permission -> permission.value })
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
        }

    private val adapter: ChattingRoomRecyclerViewAdapter =
        ChattingRoomRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_vp_item_coordinate, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        chattingViewModelObserve()
    }

    private fun init() {
        binding.rvEnterableRoomVpItemHome.adapter = adapter

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            val locationManager =
                ContextCompat.getSystemService(requireContext(), LocationManager::class.java)
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 10f
            ) { location ->
                val latLng = LatLng(location)
                binding.srlCoordinate.setOnRefreshListener {
                    viewModel.getRoomsByCoordinate(latLng.latitude , latLng.longitude)
                }
                viewModel.getRoomsByCoordinate(latLng.latitude , latLng.longitude)
            }
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessCoordinateRooms.observe(viewLifecycleOwner) {
            adapter.setList(it)
            roomVis.value = it.isNotEmpty()
            binding.srlCoordinate.isRefreshing = false
        }

        isFailureCoordinateRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun chattingViewModelObserve() = with(chattingViewModel) {
        chatList.observe(requireActivity()) {
            val recentRoomId = it.last().roomID
            val recentRoom = adapter.getList().find { it.roomID == recentRoomId }
            val list = adapter.getList()
            list.apply {
                remove(recentRoom)
                add(0, recentRoom!!)
            }
            adapter.setList(list)
        }
    }

    override fun onClick(room: RoomInfo) {
        val bundle = Bundle().apply {
            putInt("roomID", room.roomID)
        }
        binding.tvTitleJoinChatting.text = room.roomName
        binding.tvUserJoinChatting.text = room.memberLimit.toString()
        binding.constraintLayout.transitionToEnd()
        binding.btnCloseJoinChatting.setOnClickListener { binding.constraintLayout.transitionToStart() }
        binding.btnJoinChatting.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_setProfileFragment,
                bundle
            )
        }
    }
}