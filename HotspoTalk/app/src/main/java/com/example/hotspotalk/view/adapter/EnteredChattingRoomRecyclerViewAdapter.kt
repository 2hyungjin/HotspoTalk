package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingEnteredRoomBinding

class EnteredChattingRoomRecyclerViewAdapter(private val onClickListener: OnClickChattingRoomListener) :
    RecyclerView.Adapter<EnteredChattingRoomRecyclerViewAdapter.ViewHolder>() {

    interface OnClickChattingRoomListener {
        fun onClick(room: EnteredRoomInfo)
    }


    private val list = mutableListOf<EnteredRoomInfo>()

    private lateinit var binding: FragmentHomeRvItemChattingEnteredRoomBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentHomeRvItemChattingEnteredRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.data = list[position]

        binding.tvRoomNameRvItemChattingRoom.text = list[position].memberLimit.toString()
        binding.layoutRvItemChattingRoom.setOnClickListener {
            onClickListener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<EnteredRoomInfo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getList() = list
}