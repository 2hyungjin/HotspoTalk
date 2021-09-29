package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeVpItemBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter

class ViewPagerItemHomeFragment : Fragment() {

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


        if (adapter.list.isEmpty()) {
            binding.rvChattingRoomVpItemHome.visibility = GONE
            binding.layoutNoChatVpItemHome.visibility = VISIBLE
        } else {
            binding.rvChattingRoomVpItemHome.visibility = VISIBLE
            binding.layoutNoChatVpItemHome.visibility = GONE
        }

        if (participateAdapter.list.isEmpty()) {
            binding.rvParticipateChattingRoomVpItemHome.visibility = GONE
            binding.layoutNoChatVpItemHome.visibility = VISIBLE
        } else {
            binding.rvParticipateChattingRoomVpItemHome.visibility = VISIBLE
            binding.layoutNoChatVpItemHome.visibility = GONE
        }
    }

    private fun init() {
        binding.rvChattingRoomVpItemHome.adapter = adapter
    }
}