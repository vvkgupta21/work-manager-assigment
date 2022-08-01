package personal.project.workmanagerassignment.webService

import personal.project.workmanagerassignment.room.PostModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WebService {

    @GET("/posts")
    suspend fun getPost(): Response<ArrayList<PostModel>>

}

object PostService {
    val webService: WebService by lazy {
        retrofit.create(WebService::class.java)
    }
}