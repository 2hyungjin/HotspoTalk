package com.example.hotspotalk.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hotspotalk.R
import com.example.hotspotalk.databinding.FragmentCreateRoomBinding

/**
 * 방 추가 프래그먼트
 * 거리별, 지역별로 방을 새로 만들 수 있다
 */

class CreateRoomFragment : Fragment() {

    private lateinit var binding: FragmentCreateRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateRoomBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            radioGroupPosition.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    radioButtonDistance.id -> {
                        mapCreateRoom.visibility = View.VISIBLE
                        linearLayoutCreateRoom.visibility = View.GONE
                    }
                    radioButtonArea.id -> {
                        linearLayoutCreateRoom.visibility = View.VISIBLE
                        mapCreateRoom.visibility = View.GONE
                    }
                }
            }

            btnCreateRoom.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}