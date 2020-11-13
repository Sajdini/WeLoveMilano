package com.gsixacademy.android.welovemilano.adapter

import com.gsixacademy.android.welovemilano.models.Restaurant



    sealed class MarkerClickEvent {
        data class OnMarkerClickEvent(val markerClickEvent: Restaurant?):MarkerClickEvent()

    }
