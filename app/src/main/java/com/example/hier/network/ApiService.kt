package com.example.hier.network

import androidx.lifecycle.LiveData
import com.example.hier.models.Room
import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.ReservationNetworkModel
import com.example.hier.util.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService
{
    //TODO add URL (api/login)
    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    fun getReservations(): Response<RootReservationNetworkModel> {
        TODO("Not yet implemented")
    }

    @GET("api/Location")
    suspend fun getLocations(): Response<List<LocationNetworkModel>>

    @POST("/api/Reservation/meetingroom")
    suspend fun addReservation( reservation: ReservationPostModel): Response<ReservationPostModel>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(@Query("neededseats") neededseats: Int,@Query("locationid") locationid: Int,@Query("date") date: String) : Response<List<MeetingRoomNetworkModel>>

    @GET("api/reservation/")
    suspend fun getReservations(@Query("date")date: Long): Response<List<ReservationNetworkModel>>

    @POST("api/reservation/cowork")
    suspend fun postCoworkReservation( reservation: ReservationPostModel)

    /* suspend fun loginUser(username: String, password: String): Response<LoginResponse> {
         if (username == "admin" && password == "admin") {
             val loginres = LoginResponse(false, "", User());
             Log.e("ApiService", "Got into loginUser method")
             return Response.success(loginres)
         } else {
             return Response.error(1, null)
         }
     }*/
}