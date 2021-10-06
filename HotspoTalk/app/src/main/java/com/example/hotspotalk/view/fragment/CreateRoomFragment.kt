package com.example.hotspotalk.view.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentCreateRoomBinding
import com.naver.maps.geometry.BuildConfig.APPLICATION_ID
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationSource
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.CameraUpdate

import com.naver.maps.map.overlay.LocationOverlay




/**
 * 방 추가 프래그먼트
 * 거리별, 지역별로 방을 새로 만들 수 있다
 */

class CreateRoomFragment : Fragment() {

    private lateinit var binding: FragmentCreateRoomBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateRoomBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.all { permission -> permission.value == true })
            else {
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
            }
        }


        with(binding) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
                return
            } else {
                val locationManager = getSystemService(requireContext(), LocationManager::class.java)
                locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10f) { location ->

                    val latLng = LatLng(location)

                    mapCreateRoom.getMapAsync {
                        val locationOverlay: LocationOverlay = it.locationOverlay
                        locationOverlay.isVisible = true
                        locationOverlay.position = latLng
                        locationOverlay.bearing = location.bearing

                        it.moveCamera(CameraUpdate.scrollTo(latLng))
                    }
                }
            }

            radioGroupPosition.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    radioButtonDistance.id -> {
                        linearLayoutDistanceCreateRoom.visibility = View.VISIBLE
                        linearLayoutRadioCreateRoom.visibility = View.GONE
                    }
                    radioButtonArea.id -> {
                        linearLayoutRadioCreateRoom.visibility = View.VISIBLE
                        linearLayoutDistanceCreateRoom.visibility = View.GONE
                    }
                }
            }

            btnCreateRoom.setOnClickListener {
                findNavController().navigateUp()
            }

            toolbarCreateRoom.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}