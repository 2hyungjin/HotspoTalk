package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeBinding
import com.example.hotspotalk.view.adapter.HomeViewPagerAdapter
import com.example.hotspotalk.viewmodel.HomeViewModel
import com.example.hotspotalk.viewmodel.factory.HomeViewModelFactory

/**
 * 홈 프래그먼트
 * 채팅 목록을 확인할 수 있음
 */

class HomeFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }

    private val factory: HomeViewModelFactory by lazy { HomeViewModelFactory() }
    private val viewModel: HomeViewModel by viewModels { factory }
    private lateinit var binding: FragmentHomeBinding

    private val adapter: HomeViewPagerAdapter by lazy { HomeViewPagerAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.btnCreateRoomHome.setOnClickListener {
            navigateToCreateRoom()
        }
    }

    private fun init() {
        adapter.setList(listOf(HomeViewPagerItemFragment(), HomeViewPagerItemFragment()))
        binding.vpHome.adapter = adapter

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.radioButtonTownChatHome.apply {
                            isChecked = false
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        }
                        binding.radioButtonParticipateHome.apply {
                            isChecked = true
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        }
                    }
                    1 -> {
                        binding.radioButtonTownChatHome.apply {
                            isChecked = true
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        }
                        binding.radioButtonParticipateHome.apply {
                            isChecked = false
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        }
                    }
                }
            }
        })

        binding.radioGroupHome.setOnCheckedChangeListener { _, checkedId ->
            binding.vpHome.currentItem = when (checkedId) {
                binding.radioButtonParticipateHome.id -> 0
                binding.radioButtonTownChatHome.id -> 1
                else -> 0
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // 메모리에서 삭제
        binding.vpHome.adapter = null
    }

    private fun navigateToCreateRoom() {
        navController.navigate(R.id.action_homeFragment_to_createRoomFragment)
    }
}