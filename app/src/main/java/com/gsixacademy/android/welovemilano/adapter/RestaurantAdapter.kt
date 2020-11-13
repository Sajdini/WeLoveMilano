package com.gsixacademy.android.welovemilano.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gsixacademy.android.welovemilano.R
import com.gsixacademy.android.welovemilano.models.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_item.view.*
import kotlinx.android.synthetic.main.fragment_places.view.*
import java.util.*
import kotlin.collections.ArrayList

class RestaurantAdapter(val restaurantData: ArrayList<Restaurant>, val restaurantClickEvent:(RestaurantClickEvent)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return restaurantData.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.bindData(restaurantData[position], position)

    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(itemModel: Restaurant, position: Int) {
            itemView.text_view_name.text = itemModel.restaurant?.name
            itemView.text_view_description.text = itemModel.restaurant?.cuisines
            itemView.text_view_adress.text = itemModel.restaurant?.location?.address
            itemView.text_view_rating.setText("${itemModel.restaurant?.user_rating?.aggregate_rating?.toDouble()}/5 -" )
            var rating=0.0

            if(itemModel.restaurant?.user_rating?.aggregate_rating?.toDouble()!=null){
                rating= itemModel.restaurant.user_rating!!.aggregate_rating!!.toDouble()
                if(rating <2){
                    Picasso.get().load(R.drawable.rate_02_hdpi).fit().into(itemView.rating_image_view)
                }else if(rating>=2 && rating<3){
                    Picasso.get().load(R.drawable.rate_04_hdpi).into(itemView.rating_image_view)
                }else if (rating>=3 && rating<4){
                    Picasso.get().load(R.drawable.rate_06_hdpi).into(itemView.rating_image_view)
                }else {
                    Picasso.get().load(R.drawable.rate_08_hdpi).into(itemView.rating_image_view)
                }
            }
            itemView.text_view_review.text = itemModel.restaurant?.user_rating?.rating_text

            if (itemModel.restaurant?.featured_image!= null && itemModel.restaurant.featured_image!!.isNotEmpty()) {
                Picasso.get().load( itemModel.restaurant.featured_image).centerCrop().fit()
                    .into(itemView.image_view_restaurant)}

            itemView.activity_list_item.setOnClickListener { restaurantClickEvent.invoke( RestaurantClickEvent.OnRestaurantClickEvent(itemModel)) }

            }

        }


    }
