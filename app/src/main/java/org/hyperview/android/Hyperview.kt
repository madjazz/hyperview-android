package org.hyperview.android

import retrofit2.http.GET
import retrofit2.Call

interface HyperviewApi {
    @GET("/")
    fun getRoot(): Call<String>
}
