package com.gsixacademy.android.welovemilano.adapter

import com.gsixacademy.android.welovemilano.models.Restaurant


sealed class RestaurantClickEvent {
    data class OnRestaurantClickEvent(val rest: Restaurant?):RestaurantClickEvent()

}
