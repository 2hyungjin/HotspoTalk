package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.response.MemberInfo

class UserListAdapter : ListAdapter<MemberInfo, UserListAdapter.ViewHolder>(MemberDiffUtil()) {
    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvUserName: TextView = view.findViewById<TextView>(R.id.tv_name_rv_item_user)
        fun bind(memberInfo: MemberInfo) {
            tvUserName.text = memberInfo.nickName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MemberDiffUtil : DiffUtil.ItemCallback<MemberInfo>() {
    override fun areItemsTheSame(oldItem: MemberInfo, newItem: MemberInfo): Boolean {
        return oldItem.accountId == newItem.accountId
    }

    override fun areContentsTheSame(oldItem: MemberInfo, newItem: MemberInfo): Boolean {
        return oldItem == newItem
    }
}