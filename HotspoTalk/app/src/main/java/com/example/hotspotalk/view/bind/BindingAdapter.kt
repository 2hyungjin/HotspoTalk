package com.example.hotspotalk.view.bind

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.view.adapter.ChattingRoomRecyclerViewAdapter

@BindingAdapter("setVisible")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = when(isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("setInvisible")
fun View.setInvisible(isInvisible: Boolean) {
    this.visibility = when(isInvisible) {
        true -> View.INVISIBLE
        false -> View.VISIBLE
    }
}

@BindingAdapter("loadUrl")
fun ImageView.setImage(url: String) {
    Glide.with(context).load(url).into(this)
}

