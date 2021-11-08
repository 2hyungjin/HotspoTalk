package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingRoomBinding

class ChattingRoomRecyclerViewAdapter(private val onClickListener: OnClickChattingRoomListener) :
    RecyclerView.Adapter<ChattingRoomRecyclerViewAdapter.ViewHolder>() {

    interface OnClickChattingRoomListener {
        fun onClick(room: RoomInfo)
    }


    private val list = mutableListOf<RoomInfo>()

    private lateinit var binding: FragmentHomeRvItemChattingRoomBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentHomeRvItemChattingRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.data = list[position]

        binding.tvUserRvItemChattingRoom.text = list[position].memberLimit.toString()
        binding.layoutRvItemChattingRoom.setOnClickListener {
            onClickListener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<RoomInfo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getList() = list
}