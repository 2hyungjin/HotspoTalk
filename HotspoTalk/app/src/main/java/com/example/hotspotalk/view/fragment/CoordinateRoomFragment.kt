package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeVpItemCoordinateBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.CoordinateRoomViewModel
import com.example.hotspotalk.viewmodel.EnteredRoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.function.Consumer


@AndroidEntryPoint
class CoordinateRoomFragment : Fragment(),
    ChattingRoomRecyclerViewAdapter.OnClickChattingRoomListener {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeVpItemCoordinateBinding

    private val viewModel: CoordinateRoomViewModel by viewModels()
    private val enteredRoomViewModel: EnteredRoomViewModel by activityViewModels()

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
        } else if (!isEnabledSetting()) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            val locationManager = requireContext().getSystemService(LocationManager::class.java)

            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    Log.d("TAG", "onLocationChanged: $location")
                    viewModel.getRoomsByCoordinate(location.latitude, location.longitude)
                }

                override fun onProviderDisabled(provider: String) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onLocationChanged(locations: MutableList<Location>) {}
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0f,
                locationListener
            )

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0f,
                locationListener
            )
        }
    }

    private fun observe() = with(viewModel) {
        isSuccessCoordinateRooms.observe(viewLifecycleOwner) {
            val notEnteredRoom =
                it.filterNot { enteredRoomViewModel.enteredRoom.value?.any { entered -> it.roomID == entered.roomID } == true }
            adapter.setList(notEnteredRoom)
            if (notEnteredRoom.isEmpty()) {
                roomVis.postValue(false)
            }
            roomVis.value = it.isNotEmpty()
        }

        isFailureCoordinateRooms.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onClick(room: RoomInfo) {
        val bundle = Bundle().apply {
            putInt("roomID", room.roomID)
            putString("name", room.roomName)
            putBoolean("existPW", room.existPW ?: false)
        }
        binding.tvTitleJoinChatting.text = room.roomName
        binding.constraintLayout.transitionToEnd()
        binding.btnCloseJoinChatting.setOnClickListener { binding.constraintLayout.transitionToStart() }
        binding.btnJoinChatting.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_setProfileFragment,
                bundle
            )
        }
    }

    private fun isEnabledSetting(): Boolean {
        val locationManager = requireContext().getSystemService(LocationManager::class.java)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}