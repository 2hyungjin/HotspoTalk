package com.example.hotspotalk.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotspotalk.R
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import org.w3c.dom.Text
import java.lang.RuntimeException

class MessageListAdapter :
    RecyclerView.Adapter<BaseViewHolder<Message>>() {
    var messageList = arrayListOf<Message>()
    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].messageType) {
            MessageType.MINE -> 1
            MessageType.YOURS -> -1
            MessageType.COMMAND -> 0
        }
    }

    inner class MyMessageViewHolder(private val view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {
            val content: TextView = view.findViewById(R.id.tv_content_mine)
            val time: TextView = view.findViewById(R.id.tv_time_stamp_mine)
            content.text = item.content
            time.text = item.timestamp
        }

    }

    inner class YourMessageViewHolder(private val view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {
            val content: TextView = view.findViewById(R.id.tv_content_yours)
            val time: TextView = view.findViewById(R.id.tv_time_stamp_yours)
            val nickname: TextView = view.findViewById(R.id.tv_nickname_yours)

            content.text = item.content
            time.text = item.timestamp
            nickname.text = item.nickname
        }
    }

    inner class CommandMessageViewHolder(private val view: View) :
        BaseViewHolder<Message>(view) {
        override fun bind(item: Message) {
            val command: TextView = view.findViewById(R.id.tv_message_command)
            command.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Message> {
        val viewHolder = when (viewType) {
            1 -> MyMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_mine, parent, false)
            )
            -1 -> YourMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_yours, parent, false)
            )
            else -> CommandMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_rv_item_chat_command, parent, false)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Message>, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int = messageList.size
    fun submitList(list: List<Message>) {
        messageList.addAll(list)
        notifyDataSetChanged()
    }

    fun addMessage(message: Message) {
        messageList.add(message)
        notifyDataSetChanged()
    }
}

class MessageDifferenceUtil : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return false
    }
}