package com.example.hotspotalk.view.adapter

import android.graphics.Color
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

        with(list[position]) {
            binding.tvUserRvItemChattingRoom.text = memberLimit.toString()
            binding.layoutRvItemChattingRoom.setOnClickListener {
                onClickListener.onClick(this)
            }
            with(binding.tvUserRvItemChattingRoom) {
                text = "${memberCount}/${memberLimit}"
                this.setTextColor(
                    if (memberCount == memberLimit) {
                        Color.parseColor("#F44336")
                    } else {
                        Color.parseColor("#000000")
                    }
                )
            }
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