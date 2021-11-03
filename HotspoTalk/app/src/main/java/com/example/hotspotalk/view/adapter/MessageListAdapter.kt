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
    ListAdapter<Message, BaseViewHolder<Message>>(MessageDifferenceUtil()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).messageType) {
            MessageType.MINE -> 1
            MessageType.YOURS -> -1
            MessageType.COMMAND -> 0
        }
    }

    inner class MyMessageViewHolder(view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {

        }

    }

    inner class YourMessageViewHolder(view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {

        }
    }

    inner class CommandMessageViewHolder(view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Message> {
        val viewHolder = when (viewType) {
            1 -> MyMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_mine, parent)
            )
            -1 -> YourMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_yours, parent)
            )
            else -> CommandMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_command, parent)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Message>, position: Int) {
        holder.bind(getItem(position))
    }
}

class MessageDifferenceUtil : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.message.hashCode() == newItem.message.hashCode()
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}