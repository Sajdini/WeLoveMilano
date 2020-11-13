package com.gsixacademy.android.welovemilano.api
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.gsixacademy.android.welovemilano.models.MilanoListResponse
import com.gsixacademy.android.welovemilano.models.RestaurantData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
const val apiKey="a54d10c5efb20457f1f386d39d37540d"
interface MilanoDatabaseApi{
    @Headers("Accept: application/json", "user-key: a54d10c5efb20457f1f386d39d37540d")
    @GET("search")
    fun getSearch(@Query("entity_id")entity_id: Int, @Query("entity_type")entity_type:String):Call<MilanoListResponse>
}