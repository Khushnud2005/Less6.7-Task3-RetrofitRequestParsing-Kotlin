package uz.example.less67_task3_retrofitrequestparsing_kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private val IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    private val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val posterService: PosterService = retrofit.create(PosterService::class.java)

}