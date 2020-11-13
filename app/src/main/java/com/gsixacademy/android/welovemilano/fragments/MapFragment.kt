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
import com.google.android.gms.maps.model.*
import com.gsixacademy.android.welovemilano.R
import com.gsixacademy.android.welovemilano.api.ApiServiceBuilder
import com.gsixacademy.android.welovemilano.api.MilanoDatabaseApi
import com.gsixacademy.android.welovemilano.models.MilanoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener

class MapFragment: Fragment(),OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var marker: Marker
    private var milanoListResponse: MilanoListResponse? = null
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
        val call = request.getSearch(258, "city")
        call.enqueue(object : Callback<MilanoListResponse> {
            override fun onFailure(call: Call<MilanoListResponse>, t: Throwable) {
                Toast.makeText(activity, "api error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<MilanoListResponse>,
                response: Response<MilanoListResponse>
            ) {
                if (response.isSuccessful) {
                    milanoListResponse = response.body()
                    if (::map.isInitialized) {
                        val builder = LatLngBounds.builder()
                        for (item in milanoListResponse?.restaurants!!) {
                            Log.d("restorantName", "${item.restaurant?.name}")
                            val markerOptions = MarkerOptions().position(
                                LatLng(
                                    (item.restaurant?.location?.latitude ?: 0.0),
                                    (item.restaurant?.location?.longitude ?: 0.0)

                                )
                            )
                                .title(item.restaurant?.name)
                                .snippet(getString(R.string.see_more))
                            builder.include(markerOptions.position)
                            map.addMarker(markerOptions).tag = item.restaurant?.id
                            val bounds = builder.build()
                            val padding = 90
                            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                            map.moveCamera(cameraUpdate)

                        }
                    }
                }
            }
        })

    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.my_map_style))

    }

}



