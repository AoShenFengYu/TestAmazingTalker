package com.aoshenfengyu.testamazingtalker.request

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class RequestManager private constructor(context: Context) {

    var retrofit: Retrofit

    companion object {
        const val CONNECT_TIMEOUT: Long = 15 * 1000
        const val WRITE_TIMEOUT: Long = 15 * 1000
        const val READ_TIMEOUT: Long = 15 * 1000

        const val BASE_URL = "https://api.amazingtalker.com/"

        private var instance: RequestManager? = null
        private var staticContext: Context? = null

        fun init(c: Context) {
            staticContext = c
        }

        fun getInstance(): RequestManager {
            if (staticContext == null) {
                throw RuntimeException("You must initialize this manager before getting instance")
            }
            if (instance == null) {
                instance = RequestManager(staticContext!!)
            }
            return instance!!
        }
    }

    init {
        var okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        okHttpClientBuilder.addInterceptor(RequestInterceptor(context))

        val okHttpClient = okHttpClientBuilder.build()
        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }
}
