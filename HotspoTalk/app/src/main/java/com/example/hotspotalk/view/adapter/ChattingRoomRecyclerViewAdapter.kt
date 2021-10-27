package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.databinding.FragmentHomeRvItemChattingRoomBinding

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

    private val list = mutableListOf<RoomInfo>()

    private lateinit var binding: FragmentHomeRvItemChattingRoomBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentHomeRvItemChattingRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.data = list[position]

//        binding.apply {
//            tvUserNicknameRvItemChattingRoom.text = data.
//            tvUserRvItemChattingRoom.text = data.user.toString()

//            if (data.isNotify) cvNotifyRvItemChattingRoom.visibility = View.VISIBLE
//            if (data.isSecret) ivSecretRvTemChattingRoom.visibility = View.VISIBLE

//            Glide.with(ivProfileImgRvItemChattingRoom)
//                .load(data.nickname)
//        }

        binding.layoutRvItemChattingRoom.setOnClickListener {
            setOnClickChattingRoomListener.onClick(position)
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