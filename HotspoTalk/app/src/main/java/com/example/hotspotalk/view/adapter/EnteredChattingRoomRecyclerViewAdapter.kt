package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingEnteredRoomBinding

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class EnteredChattingRoomRecyclerViewAdapter(private val onClickListener: OnClickChattingRoomListener) :
    ListAdapter<EnteredRoomInfo, EnteredChattingRoomRecyclerViewAdapter.ViewHolder>(
        EnteredRoomInfoDiff()
    ) {
    private lateinit var binding: FragmentHomeRvItemChattingEnteredRoomBinding

    inner class ViewHolder() :
        RecyclerView.ViewHolder(binding.root) {

    }

    interface OnClickChattingRoomListener {
        fun onClick(room: EnteredRoomInfo)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentHomeRvItemChattingEnteredRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = getItem(position)
        binding.data = room

        binding.tvRoomNameRvItemChattingRoom.text = room.memberLimit.toString()
        binding.layoutRvItemChattingRoom.setOnClickListener {
            onClickListener.onClick(room)
        }
    }
}

class EnteredRoomInfoDiff : DiffUtil.ItemCallback<EnteredRoomInfo>() {
    override fun areItemsTheSame(oldItem: EnteredRoomInfo, newItem: EnteredRoomInfo): Boolean {
        return oldItem.lastChatting == newItem.lastChatting
    }

    override fun areContentsTheSame(oldItem: EnteredRoomInfo, newItem: EnteredRoomInfo): Boolean {
        return oldItem.lastChatting == newItem.lastChatting
    }
}
