package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingEnteredRoomBinding

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.hotspotalk.R

class EnteredChattingRoomRecyclerViewAdapter(private val onClickListener: OnClickChattingRoomListener) :
    RecyclerView.Adapter<EnteredChattingRoomRecyclerViewAdapter.ViewHolder>(
    ) {
    val enteredRoomList = arrayListOf<EnteredRoomInfo>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRoomNameRvItemChattingRoom =
            view.findViewById<TextView>(R.id.tv_room_name_rv_item_chatting_room)
        val layoutRvItemChattingRoom = view.findViewById<View>(R.id.layout_rv_item_chatting_room)
        val tvLastChattingRvItemChattingRoom =
            view.findViewById<TextView>(R.id.tv_last_chatting_rv_item_chatting_room)

    }

    interface OnClickChattingRoomListener {
        fun onClick(room: EnteredRoomInfo)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_home_rv_item_chatting_entered_room, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = enteredRoomList[position]
        holder.tvRoomNameRvItemChattingRoom.text = room.roomName
        holder.layoutRvItemChattingRoom.setOnClickListener {
            onClickListener.onClick(room)
        }
        holder.tvLastChattingRvItemChattingRoom.text = room.lastChatting
    }

    override fun getItemCount(): Int = enteredRoomList.size

    fun submitList(list: List<EnteredRoomInfo>) {
        enteredRoomList.clear()
        enteredRoomList.addAll(list)
        notifyDataSetChanged()
    }

}


