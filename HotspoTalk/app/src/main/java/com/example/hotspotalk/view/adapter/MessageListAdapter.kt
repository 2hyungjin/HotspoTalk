package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import java.lang.RuntimeException

class MessageListAdapter :
    ListAdapter<Message, RecyclerView.ViewHolder>(MessageDifferenceUtil()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).messageType) {
            MessageType.MINE -> 1
            MessageType.YOURS -> -1
            MessageType.COMMAND -> 0
        }
    }

    inner class MyMessageViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

    }

    inner class YourMessageViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

    }

    inner class CommandMessageViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            1 -> MyMessageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent)
            )
            -1 -> YourMessageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent)
            )
            else -> CommandMessageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is YourMessageViewHolder -> {

            }
        }

    }
}

class MessageDifferenceUtil : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.message.messageId == newItem.message.messageId
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}