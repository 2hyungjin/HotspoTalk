package com.example.hotspotalk.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotspotalk.R
import com.example.hotspotalk.viewmodel.ChattingViewModel

class ChattingFragment : Fragment() {

    companion object {
        fun newInstance() = ChattingFragment()
    }

    private lateinit var viewModel: ChattingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChattingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}