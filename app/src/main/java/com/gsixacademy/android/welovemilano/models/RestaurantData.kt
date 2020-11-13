package com.gsixacademy.android.welovemilano.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class RestaurantData(): Parcelable {
    var id: Int = 0
    var name: String? = null
    var url: String? = null
    var cuisines: String? = null
    var timings: String? = null
    var average_cost_for_two: Int = 0
    var currency: String? = null
    var thumb: String? = null
    var photos_url: String? = null
    var menu_url: String? = null
    var featured_image: String? = null
    var events_url: String? = null
    var phone_numbers: String? = null
    //val highlights: Highlights?,
    var user_rating: UserRating? = null
    var location: Location? = null
    var establishment: ArrayList<String>?=null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        url = parcel.readString()
        cuisines = parcel.readString()
        timings = parcel.readString()
        average_cost_for_two = parcel.readInt()
        currency = parcel.readString()
        thumb = parcel.readString()
        photos_url = parcel.readString()
        menu_url = parcel.readString()
        featured_image = parcel.readString()
        events_url = parcel.readString()
        phone_numbers = parcel.readString()
        location=parcel.readParcelable(Location::class.java.classLoader)
        user_rating=parcel.readParcelable(UserRating::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(cuisines)
        parcel.writeString(timings)
        parcel.writeInt(average_cost_for_two)
        parcel.writeString(currency)
        parcel.writeString(thumb)
        parcel.writeString(photos_url)
        parcel.writeString(menu_url)
        parcel.writeString(featured_image)
        parcel.writeString(events_url)
        parcel.writeString(phone_numbers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantData> {
        override fun createFromParcel(parcel: Parcel): RestaurantData {
            return RestaurantData(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantData?> {
            return arrayOfNulls(size)
        }
    }

}

