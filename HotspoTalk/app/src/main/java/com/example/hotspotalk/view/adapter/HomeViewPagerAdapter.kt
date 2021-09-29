package com.example.hotspotalk.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val list = mutableListOf<Fragment>()

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]

    fun setList(list: List<Fragment>) {
        this.list.addAll(list)
    }
}