package io.github.initrc.dummy.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: String): Post

    @GET("posts")
    suspend fun getPosts(): List<Post>
}

data class Post(val id: Int, val userId: Int, val title: String, val body: String)

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val dummyService: DummyService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<DummyService>()
    }
}

