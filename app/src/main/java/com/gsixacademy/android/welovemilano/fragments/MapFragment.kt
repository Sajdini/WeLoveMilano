package com.gsixacademy.android.welovemilano.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.gsixacademy.android.welovemilano.R
import com.gsixacademy.android.welovemilano.api.ApiServiceBuilder
import com.gsixacademy.android.welovemilano.api.MilanoDatabaseApi
import com.gsixacademy.android.welovemilano.models.Location
import com.gsixacademy.android.welovemilano.models.MilanoListResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapFragment: Fragment(),OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private var milanoListResponse:MilanoListResponse?=null
    private var location:Location?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment

        mapFragment.getMapAsync(this)
        val request = ApiServiceBuilder.buildService(MilanoDatabaseApi::class.java)
        val call=request.getSearch(258,"city")
        call.enqueue(object :Callback<MilanoListResponse>{
            override fun onFailure(call: Call<MilanoListResponse>, t: Throwable) {
                Toast.makeText(activity,"api error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<MilanoListResponse>,
                response: Response<MilanoListResponse>
            ) {
                if (response.isSuccessful){
                    milanoListResponse = response.body()
                    for (item in milanoListResponse?.restaurants!!) {
                        Log.d("restorantName","${item.restaurant?.name}")
                        val builder=LatLngBounds.builder()
                        val markerOptions = MarkerOptions().position(
                            LatLng(location?.latitude!!.toDouble(),location?.longitude!!.toDouble()))
                            .title(item.restaurant?.name)
                            .snippet(getString(R.string.see_more))
                        builder.include(markerOptions.position)
                        map.addMarker(markerOptions).tag = item.restaurant?.id
                        val bounds=builder.build()
                        val padding=90
                        val cameraUpdate= CameraUpdateFactory.newLatLngBounds(bounds,padding)
                        map.moveCamera(cameraUpdate)
                    }
                }
            }

        })
    }


    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            p0.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.my_map_style))
        }

    }


}



