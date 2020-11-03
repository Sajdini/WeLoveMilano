package com.gsixacademy.android.welovemilano.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gsixacademy.android.welovemilano.R
import com.gsixacademy.android.welovemilano.models.*
import kotlinx.android.synthetic.main.activity_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class RestaurantAdapter(val restaurantData: ArrayList<Restaurant>, val restaurantClickEvent:(RestaurantClickEvent)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return  restaurantData.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder=holder as MyViewHolder
        myViewHolder.bindData(restaurantData[position],position)

   }

inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
    fun bindData(itemModel:Restaurant,position:Int){
        itemView.text_view_name.text=itemModel.restaurant?.name
        itemView.text_view_name.text=itemModel.restaurant?.id
        itemView.text_view_description.text=itemModel.restaurant?.cuisines
    //    itemView.text_view_adress.text=itemModel.restaurant?.location?.adress

        itemView.activity_list_item.setOnClickListener {
            restaurantClickEvent.invoke(RestaurantClickEvent.OnRestaurantClickEvent(itemModel))
        }

    }

}


}