package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeVpItemBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter

class HomeViewPagerItemFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeVpItemBinding

    private val participateAdapter: ChattingRoomRecyclerViewAdapter by lazy { ChattingRoomRecyclerViewAdapter() }
    private val adapter: ChattingRoomRecyclerViewAdapter by lazy { ChattingRoomRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_vp_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        adapter.setOnClickChattingRoomListener {
            val bundle = Bundle()
            bundle.putInt("id", it)
            navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
        }
    }

    private fun init() {
        binding.rvChattingRoomVpItemHome.adapter = adapter

        // 동네 챗팅
        if (adapter.list.isEmpty()) {
            binding.rvChattingRoomVpItemHome.visibility = GONE
            binding.layoutNoChatVpItemHome.visibility = VISIBLE
        } else {
            binding.rvChattingRoomVpItemHome.visibility = VISIBLE
            binding.layoutNoChatVpItemHome.visibility = GONE
        }

        // 참가한 챗팅
        if (participateAdapter.list.isEmpty()) {
            binding.rvParticipateChattingRoomVpItemHome.visibility = GONE
            binding.layoutNoChatVpItemHome.visibility = VISIBLE
        } else {
            binding.rvParticipateChattingRoomVpItemHome.visibility = VISIBLE
            binding.layoutNoChatVpItemHome.visibility = GONE
        }
    }


}