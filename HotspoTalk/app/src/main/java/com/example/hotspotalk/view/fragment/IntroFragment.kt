package com.example.hotspotalk.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hotspotalk.R
import com.example.hotspotalk.viewmodel.ChattingViewModel

/**
 * 인트로 프래그먼트
 * 첫 실행 시 앱에 대한 설명을 하는 곳
 */

class IntroFragment : Fragment() {
    private val chattingFragment:ChattingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chattingFragment.chatList.observe(viewLifecycleOwner, Observer {
        })
    }

}