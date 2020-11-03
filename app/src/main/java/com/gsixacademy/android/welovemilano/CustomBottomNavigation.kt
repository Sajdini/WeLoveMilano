package com.gsixacademy.android.welovemilano

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.bottom_navigation.view.*

class CustomBottomNavigation:FrameLayout {

    lateinit var mInflater: LayoutInflater

    constructor(context:Context):super(context){
        init(context)
    }
    constructor(context: Context,attr:AttributeSet?):super(context,attr){
        init(context)
    }
    constructor(context: Context,attr:AttributeSet?,style:Int):super(context,attr,style){
        init(context)
    }


    fun init(context: Context){
        mInflater= LayoutInflater.from(context)
        mInflater.inflate(R.layout.bottom_navigation,this,true)

    }

    fun setSelectedTab(tabToSelect:Int){
        text_view_places.isSelected=tabToSelect==1
        text_view_map.isSelected=tabToSelect==2
        text_view_list.isSelected=tabToSelect==3
        text_view_info.isSelected=tabToSelect==4

    }

}