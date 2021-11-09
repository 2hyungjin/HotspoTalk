package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.databinding.FragmentCreateRoomBinding
import com.example.hotspotalk.viewmodel.CreateRoomViewModel
import com.google.android.material.chip.Chip
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.CircleOverlay
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint


/**
 * 방 추가 프래그먼트
 * 거리별, 지역별로 방을 새로 만들 수 있다
 */

@AndroidEntryPoint
class CreateRoomFragment : Fragment() {
    companion object {
        private const val MAX_RADIUS = 2000.0
    }

    private val viewModel: CreateRoomViewModel by viewModels()
    private lateinit var binding: FragmentCreateRoomBinding
    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.all { permission -> permission.value == true })
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateRoomBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        settingMap()
        observe()
    }

    private fun init() {
        with(binding) {
            toolbarCreateRoom.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun observe() = with(viewModel) {
        roomName.observe(viewLifecycleOwner) {
            with(binding.tilChattingRoomNameCreateRoom) {
                isErrorEnabled = it.isEmpty()
                error =
                    if (it.isEmpty()) {
                        "필수 항목입니다."
                    } else {
                        ""
                    }
            }
        }

        memberLimit.observe(viewLifecycleOwner) {
            with(binding.tilTotalNumberCreateRoom) {
                isErrorEnabled = it.isEmpty()
                error =
                    if (it.isEmpty()) {
                        "필수 항목입니다."
                    } else {
                        ""
                    }
            }
        }

        nickname.observe(viewLifecycleOwner) {
            with(binding.tilNickNameCreateRoom) {
                isErrorEnabled = it.isEmpty()
                error =
                    if (it.isEmpty()) {
                        "필수 항목입니다."
                    } else {
                        ""
                    }
            }
        }

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        isSuccess.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun settingMap() = with(binding) {
        viewModel.isMapLoading.value = true
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.isMapLoading.value = false
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return@with
        } else if (!isEnabledSetting()) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
            viewModel.isMapLoading.value = false
        } else {
            viewModel.isMapLoading.value = false
            val locationManager = requireContext().getSystemService(LocationManager::class.java)
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (location == null) {
                val locationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        settingMapByLocation(location)
                    }

                    override fun onProviderDisabled(provider: String) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onLocationChanged(locations: MutableList<Location>) {}
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    500L,
                    1f,
                    locationListener
                )
            } else {
                settingMapByLocation(location)
            }
        }
    }

    private fun settingMapByLocation(location: Location) = with(binding) {
        val latLng = LatLng(location)

        radioGroupPosition.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonDistance.id -> {
                    viewModel.areaType.value = 0
                    linearLayoutDistanceCreateRoom.visibility = View.VISIBLE
                    linearLayoutRadioCreateRoom.visibility = View.GONE
                }
                radioButtonArea.id -> {
                    viewModel.areaType.value = 1
                    linearLayoutRadioCreateRoom.visibility = View.VISIBLE
                    linearLayoutDistanceCreateRoom.visibility = View.GONE
                }
            }
            Log.d("TAG", "settingMap: ${viewModel.areaType.value}")
        }

        mapCreateRoom.getMapAsync {

            val marker = Marker()
            with(marker) {
                position = latLng
                map = it
            }
            viewModel.latitude.value = latLng.latitude
            viewModel.longitude.value = latLng.longitude
            viewModel.isMapLoading.postValue(false)

            val geocoder = Geocoder(context)
            val address = geocoder.getFromLocation(
                latLng.latitude,
                latLng.longitude,
                10
            )[0].getAddressLine(0)
            val addressList = address.split(" ")
            for (i in 1 until addressList.lastIndex) {
                val chip = Chip(context)
                chip.text = addressList[i]
                chip.isCheckable = true
                chip.addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
                    var chipAddress = ""
                    val chipItem = v as Chip
                    if (chipItem.isChecked && i > 1) {
                        for (j in 0 until i) {
                            val unCheckedChip = chipGroupAddressCreateRoom[j] as Chip
                            Log.d("CreateRoomFragment", "settingMap: ${unCheckedChip.text}")
                            if (!unCheckedChip.isChecked) {
                                unCheckedChip.isChecked = true
                            }
                            chipAddress += unCheckedChip.text.toString() + " "
                            viewModel.address.value =
                                chipAddress.substring(0, chipAddress.length - 1)
                        }
                    }
                }
                chipGroupAddressCreateRoom.addView(chip)
            }


            it.moveCamera(CameraUpdate.scrollTo(latLng))

            val circle = CircleOverlay(latLng, MAX_RADIUS)
            with(circle) {
                outlineColor = Color.BLACK
                outlineWidth = 3
                color = ResourcesCompat.getColor(
                    resources,
                    android.R.color.transparent,
                    resources.newTheme()
                )
                map = it
            }
            circle.radius = 500.0

            seekbarCreateRoom.focusable = View.FOCUSABLE
            seekbarCreateRoom.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val radius =
                        (((progress + 1).toDouble() / (seekBar?.max!! + 1).toDouble()) * MAX_RADIUS)
                    viewModel.areaDetail.value = radius.toInt()
                    circle.radius = radius
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }
    }

    private fun isEnabledSetting(): Boolean {
        val locationManager = requireContext().getSystemService(LocationManager::class.java)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}