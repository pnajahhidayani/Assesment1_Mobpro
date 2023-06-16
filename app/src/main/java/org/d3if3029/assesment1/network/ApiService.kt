package org.d3if3029.assesment1.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "indraazimi/galeri-hewan/static-api/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
//     @GET("static-api.json")
//    suspend fun getHewan(): String
}
object NilaiApi {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    enum class ApiStatus { LOADING, SUCCESS, FAILED}
}
