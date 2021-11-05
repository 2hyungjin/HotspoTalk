package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
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
import com.example.hotspotalk.databinding.FragmentHomeVpItemBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.HomeViewModel
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerItemFragment : Fragment(),
    ChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeVpItemBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private val chattingViewModel: ChattingViewModel by activityViewModels()
    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.all { permission -> permission.value })
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
        }

    private val enterableAdapter: ChattingRoomRecyclerViewAdapter =
        ChattingRoomRecyclerViewAdapter(this)
    private val notEnterableAdapter: ChattingRoomRecyclerViewAdapter =
        ChattingRoomRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_vp_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        chattingViewModelObserve()
    }

    private fun init() {
        binding.rvEnterableRoomVpItemHome.adapter = enterableAdapter
        binding.rvNotEnterableChattingRoomVpItemHome.adapter = notEnterableAdapter

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        } else {
            val locationManager =
                ContextCompat.getSystemService(requireContext(), LocationManager::class.java)
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 10f
            ) { location ->

                val latLng = LatLng(location)
                viewModel.getRoomsByCoordinate(latLng.latitude , latLng.longitude)
            }
        }
        viewModel.getEnteredRooms()
    }

    private fun observe() = with(viewModel) {
        isSuccessCoordinateRooms.observe(viewLifecycleOwner) {
            when (it) {
                null -> {
                    Toast.makeText(requireContext(), "채팅방을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                    enterableVis.value = false
                }
                else -> {
                    enterableAdapter.setList(it)
                    enterableVis.value = it.isEmpty()
                }
            }
        }

        isFailureCoordinateRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        isSuccessEnteredRooms.observe(viewLifecycleOwner) {
            when (it) {
                null -> {
                    Toast.makeText(requireContext(), "채팅방을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                    notEnterableVis.value = false
                }

                else -> {
                    notEnterableVis.value = it.isEmpty()
                    notEnterableAdapter.setList(it)
                }
            }
        }

        isFailureEnteredRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun chattingViewModelObserve() = with(chattingViewModel) {
        chatList.observe(requireActivity()) {
            val recentRoomId = it.last().roomID
            val recentRoom = enterableAdapter.getList().find { it.roomID == recentRoomId }
            val list = enterableAdapter.getList()
            list.apply {
                remove(recentRoom)
                add(0, recentRoom!!)
            }
            enterableAdapter.setList(list)
        }
    }

    override fun onClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)

    }
}