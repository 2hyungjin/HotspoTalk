package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentHomeVpItemBinding
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter
import com.example.hotspotalk.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerItemFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentHomeVpItemBinding
    private val viewModel: HomeViewModel by activityViewModels()

    private val enterableAdapter: ChattingRoomRecyclerViewAdapter = ChattingRoomRecyclerViewAdapter()
    private val notEnterableAdapter: ChattingRoomRecyclerViewAdapter = ChattingRoomRecyclerViewAdapter()

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

        observe()

        notEnterableAdapter.setOnClickChattingRoomListener {
            val bundle = Bundle()
            bundle.putInt("id", it)
            navController.navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
        }
    }

    private fun init() {
        binding.rvEnterableRoomVpItemHome.adapter = enterableAdapter
        binding.rvNotEnterableChattingRoomVpItemHome.adapter = notEnterableAdapter

        viewModel.getRoomsEnterable()
        viewModel.getRoomsNotEnterable()
    }

    private fun observe() = with(viewModel) {
        isSuccessEnterable.observe(viewLifecycleOwner) { it ->
            enterableVis.value = when {
                it.isEmpty() -> false
                else -> true
            }
        }

        isFailureEnterable.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        isSuccessNotEnterable.observe(viewLifecycleOwner) {
            notEnterableVis.value = when {
                it.isEmpty() -> false
                else -> true
            }
        }

        isFailureNotEnterable.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}