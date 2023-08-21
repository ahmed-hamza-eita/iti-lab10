package com.hamza.itilab10.network

import com.hamza.retofit_lab9.utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConnection {

   fun getRetrofit(baseUrl:String): ApiCalls {

       val interceptor = CustomInterceptor("Authorization", "Bearer your-token")
       val client = OkHttpClient.Builder()
           .addInterceptor(interceptor)
           .build()


        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiCalls::class.java)
    }

}
