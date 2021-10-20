package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.databinding.FragmentCreateRoomBinding
import com.example.hotspotalk.viewmodel.CreateRoomViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.CircleOverlay

import com.naver.maps.map.overlay.Marker


/**
 * 방 추가 프래그먼트
 * 거리별, 지역별로 방을 새로 만들 수 있다
 */

class CreateRoomFragment : Fragment() {

    companion object {
        private const val MAX_RADIUS = 500.0
    }

    private lateinit var binding: FragmentCreateRoomBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private val viewModel: CreateRoomViewModel by viewModels()

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
        viewModelStore.clear()
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.all { permission -> permission.value == true })
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
        }

        with(binding) {
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

            toolbarCreateRoom.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun observe() = with(viewModel) {
        chattingRoomName.observe(viewLifecycleOwner) {
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

        totalNumber.observe(viewLifecycleOwner) {
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

        isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        isSuccess.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun settingMap() = with(binding) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            return@with
        } else {
            val locationManager = getSystemService(requireContext(), LocationManager::class.java)
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 10f) { location ->

                val latLng = LatLng(location)

                mapCreateRoom.getMapAsync {

                    val marker = Marker()
                    with(marker) {
                        position = latLng
                        map = it
                    }

                    it.moveCamera(CameraUpdate.scrollTo(latLng))

                    val circle = CircleOverlay(latLng, MAX_RADIUS)
                    seekbarCreateRoom.focusable = View.FOCUSABLE
                    seekbarCreateRoom.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(
                            seekBar: SeekBar?,
                            progress: Int,
                            fromUser: Boolean
                        ) {
                            val radius = ((progress).toDouble() / (seekBar?.max!!).toDouble()) * MAX_RADIUS
                            tvDistanceCreateRoom.text = "${radius.toInt()}m"
                            with(circle) {
                                outlineColor = Color.BLACK
                                outlineWidth = 3
                                color = ResourcesCompat.getColor(resources, android.R.color.transparent, resources.newTheme())
                                map = it
                                setRadius(radius)
                            }
                        }
                        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                        override fun onStopTrackingTouch(seekBar: SeekBar?) {

                        }
                    })
                }
            }
        }
    }

}