package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeBinding
import com.example.hotspotalk.view.adapter.HomeViewPagerAdapter
import com.example.hotspotalk.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * 홈 프래그먼트
 * 채팅 목록을 확인할 수 있음
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var adapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
    }

    private fun init() {
        adapter = HomeViewPagerAdapter(this)
        adapter.setList(listOf(HomeViewPagerItemFragment(), HomeViewPagerItemFragment()))
        binding.vpHome.adapter = adapter

        binding.radioGroupHome.setOnCheckedChangeListener { _, checkedId ->
            binding.vpHome.currentItem = when (checkedId) {
                binding.radioNotEnterableHome.id -> 0
                binding.radioEnterableChatHome.id -> 1
                else -> 0
            }
        }

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val id = when (position) {
                    0 -> binding.radioNotEnterableHome.id
                    1 -> binding.radioEnterableChatHome.id
                    else -> -1
                }

                binding.radioGroupHome.check(id)
            }
        })
    }

    private fun observe() = with(viewModel) {}

    private fun navigateToCreateRoom() {
        navController.navigate(R.id.action_homeFragment_to_createRoomFragment)
    }
}