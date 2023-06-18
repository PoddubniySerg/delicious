package vac.test.delicious.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {

    private const val HOST = "https://gist.githubusercontent.com"

    val dataApi =
        Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DataApi::class.java)
}