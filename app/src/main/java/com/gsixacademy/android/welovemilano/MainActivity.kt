package com.gsixacademy.android.welovemilano

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.gsixacademy.android.welovemilano.api.ApiServiceBuilder
import com.gsixacademy.android.welovemilano.api.MilanoDatabaseApi
import com.gsixacademy.android.welovemilano.models.MilanoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private  var milanoListResponse: MilanoListResponse?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ApiServiceBuilder.buildService(MilanoDatabaseApi::class.java)

        val call = request.getSearch(258,"city")
        call.enqueue(object : Callback<MilanoListResponse> {
            override fun onFailure(call: Call<MilanoListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "api error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<MilanoListResponse>,
                response: Response<MilanoListResponse>
            ) {
                if (response.isSuccessful) {
                    milanoListResponse = response.body()
                    for (item in milanoListResponse?.restaurants!!) {
                        Log.d("restorantName","${item.restaurant?.name}")
                    }
                }
            }
        })

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync { this }
    }

    fun getLocationPermission(): Boolean {
        val permissions = ArrayList<String>()
        if (!isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 2000)
            return false
        } else
            return true
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return this.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 2002) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateLocationUI()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateLocationUI() {
        if (isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            map.isMyLocationEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = true
        } else {
            map.isMyLocationEnabled = false
            map.uiSettings.isMyLocationButtonEnabled = false
            getLocationPermission()
        }
    }


    override fun onMapReady(googlemap: GoogleMap) {
        map = googlemap
        map.setOnMapLoadedCallback {


        }

    }
}
