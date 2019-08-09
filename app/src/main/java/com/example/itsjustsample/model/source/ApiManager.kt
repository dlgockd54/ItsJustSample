package com.example.itsjustsample.model.source

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hclee on 2019-08-09.
 */

object ApiManager {
    val gson = GsonBuilder().setLenient().create()

    private val okhttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

    private val githubAdapter: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(
                okhttpClientBuilder
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder().header(
                                "Authorization",
                                "token 226532f9b0e7ade7febb7fb9d05d29b4db88700b"
                            ).build()
                        )
                    }
                    .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val githubApi: GithubApi = githubAdapter.create(GithubApi::class.java)
}