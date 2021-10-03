package kr.kro.fatcats.util.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseService {
    class ResponseInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            try {
                val response = chain.proceed(chain.request())
                return response.newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build()
            }catch (e : Exception){
                Log.e("ResponseInterceptor","$e")
                return chain.proceed(chain.request())
            }
        }
    }

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setLenient()
        .create()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ResponseInterceptor())
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getClient(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}