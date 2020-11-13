package com.gsixacademy.android.welovemilano.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserRating ():Parcelable {
    var aggregate_rating: String? = null
    var rating_text: String? = null

    constructor(parcel: Parcel) : this() {
        aggregate_rating = parcel.readString()
        rating_text = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(aggregate_rating)
        parcel.writeString(rating_text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserRating> {
        override fun createFromParcel(parcel: Parcel): UserRating {
            return UserRating(parcel)
        }

        override fun newArray(size: Int): Array<UserRating?> {
            return arrayOfNulls(size)
        }
    }
}
