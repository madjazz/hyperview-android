package ch.smoca.hyperview

import retrofit2.http.GET
import retrofit2.Call

interface HyperviewApi {
    @GET("/")
    fun getRoot(): Call<String>
}
