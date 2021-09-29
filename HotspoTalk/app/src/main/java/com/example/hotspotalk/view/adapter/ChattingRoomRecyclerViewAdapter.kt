package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingRoomBinding
import com.example.hotspotalk.view.model.ChattingRoom

class ChattingRoomRecyclerViewAdapter :
    RecyclerView.Adapter<ChattingRoomRecyclerViewAdapter.ViewHolder>() {

    interface OnClickChattingRoomListener {
        fun onClick(id: Int)
    }

    private lateinit var setOnClickChattingRoomListener: OnClickChattingRoomListener

    fun setOnClickChattingRoomListener(listener: (Int) -> Unit) {
        setOnClickChattingRoomListener = object : OnClickChattingRoomListener {
            override fun onClick(id: Int) {
                listener(id)
            }
        }
    }

    val list = mutableListOf<ChattingRoom>()

    private lateinit var binding: FragmentHomeRvItemChattingRoomBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentHomeRvItemChattingRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        binding.tvRoomNameRvItemChattingRoom.text = data.roomName
        binding.tvUserNicknameRvItemChattingRoom.text = data.nickname
        binding.tvUserRvItemChattingRoom.text = data.user.toString()

        if (data.isNotify) binding.cvNotifyRvItemChattingRoom.visibility = View.VISIBLE
        if (data.isSecret) binding.ivSecretRvTemChattingRoom.visibility = View.VISIBLE

        // todo 이미지 글라이드

        binding.layoutRvItemChattingRoom.setOnClickListener {
            setOnClickChattingRoomListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<ChattingRoom>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}