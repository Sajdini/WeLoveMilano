package com.gsixacademy.android.welovemilano.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gsixacademy.android.welovemilano.R
import com.gsixacademy.android.welovemilano.adapter.RestaurantAdapter
import com.gsixacademy.android.welovemilano.adapter.RestaurantClickEvent
import com.gsixacademy.android.welovemilano.api.ApiServiceBuilder
import com.gsixacademy.android.welovemilano.api.MilanoDatabaseApi

import com.gsixacademy.android.welovemilano.models.MilanoListResponse

import kotlinx.android.synthetic.main.fragment_restaurant.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = ApiServiceBuilder.buildService(MilanoDatabaseApi::class.java)
        val call = request.getSearch(258, "city")
        call.enqueue(object : Callback<MilanoListResponse> {
            override fun onFailure(call: Call<MilanoListResponse>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<MilanoListResponse>,
                response: Response<MilanoListResponse>
            ) {
                val milanoListResponse = response.body()
                val restaurantData = milanoListResponse?.restaurants


                if (restaurantData != null) {

                    recycler_view_list.adapter = RestaurantAdapter(restaurantData) {
                        if (it is RestaurantClickEvent.OnRestaurantClickEvent) {
                            var bundle = Bundle()
                            bundle.putString("restaurantName", it.rest?.restaurant?.name)
                        findNavController().navigate(R.id.action_restaurantFragment_to_restaurantDetailsFragment)
                        }

                    }

                }
            }

        })
    }
        }






