package com.gsixacademy.android.welovemilano.adapter

import android.view.View
import androidx.viewpager.widget.PagerAdapter

class FirstViewPagerAdapter(private val photoslist:ArrayList<String>):PagerAdapter() {
    override fun getCount(): Int {
return photoslist.size    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
return view==`object`
    }
}