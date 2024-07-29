package org.hyperview.android

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class HyperviewHttpService {
    companion object {
        private val api: HyperviewApi by lazy {
            Retrofit.Builder().baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(HyperviewApi::class.java)
        }

        suspend fun get(): String {
            return suspendCoroutine { continuation ->
                val call = api.getRoot()
                call.enqueue(object : retrofit2.Callback<String> {
                    override fun onResponse(
                        call: retrofit2.Call<String>,
                        response: retrofit2.Response<String>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                continuation.resume(it)
                            } ?: run {
                                continuation.resumeWithException(Exception("Response body is null"))
                            }
                        } else {
                            continuation.resumeWithException(Exception("HTTP error ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }
        }
    }
}