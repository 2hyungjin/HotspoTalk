package com.example.hotspotalk.view.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hotspotalk.R
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.databinding.FragmentHomeBinding
import com.example.hotspotalk.view.activity.MainActivity
import com.example.hotspotalk.view.adapter.HomeViewPagerAdapter
import com.example.hotspotalk.view.util.Preference.token
import com.example.hotspotalk.viewmodel.ChattingViewModel
import com.example.hotspotalk.viewmodel.CoordinateRoomViewModel
import com.example.hotspotalk.viewmodel.HomeViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

/**
 * 홈 프래그먼트
 * 채팅 목록을 확인할 수 있음
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.all { permission -> permission.value == true })
                Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
        }

    private val coordinateViewModel: CoordinateRoomViewModel by activityViewModels()

    private lateinit var adapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelStore.clear()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.btnCreateRoomHome.setOnClickListener {
            navigateToCreateRoom()
        }

        viewModel.isFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.previousBackStackEntry?.destination?.label == "fragment_login") {
                    requireActivity().finish()
                }
            }
        })
    }

    private fun init() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            viewModel.postToken(it)
        }

        adapter = HomeViewPagerAdapter(this)
        adapter.setList(listOf(EnteredRoomFragment(), CoordinateRoomFragment()))
        binding.vpHome.adapter = adapter

        binding.radioGroupHome.setOnCheckedChangeListener { _, checkedId ->
            binding.vpHome.currentItem = when (checkedId) {
                binding.radioNotEnterableHome.id -> 0
                binding.radioEnterableChatHome.id -> 1
                else -> 0
            }
        }

        binding.btnLogoutHome.setOnClickListener {
            token = ""
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val id = when (position) {
                    0 -> {
                        binding.radioNotEnterableHome.id
                    }
                    1 -> {
                        binding.radioEnterableChatHome.id
                    }
                    else -> -1
                }

                binding.radioGroupHome.check(id)
            }
        })

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
                    coordinateViewModel.getRoomsByCoordinate(location.latitude, location.longitude)
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

    private fun navigateToCreateRoom() {
        navController.navigate(R.id.action_homeFragment_to_createRoomFragment)
    }

    private fun isEnabledSetting(): Boolean {
        val locationManager = requireContext().getSystemService(LocationManager::class.java)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}