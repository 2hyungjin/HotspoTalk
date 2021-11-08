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
    RecyclerView.Adapter<EnteredChattingRoomRecyclerViewAdapter.ViewHolder>(
    ) {
    private lateinit var binding: FragmentHomeRvItemChattingEnteredRoomBinding
    val enteredRoomList = arrayListOf<EnteredRoomInfo>()

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
        val room = enteredRoomList[position]
        binding.tvRoomNameRvItemChattingRoom.text = room.roomName
        binding.layoutRvItemChattingRoom.setOnClickListener {
            onClickListener.onClick(room)
        }
        binding.tvLastChattingRvItemChattingRoom.text = room.lastChatting
    }

    override fun getItemCount(): Int = enteredRoomList.size

    fun submitList(list: List<EnteredRoomInfo>) {
        enteredRoomList.clear()
        enteredRoomList.addAll(list)
        notifyDataSetChanged()
    }

}


