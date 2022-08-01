package personal.project.workmanagerassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import personal.project.workmanagerassignment.room.PostDatabase
import personal.project.workmanagerassignment.room.PostModel
import personal.project.workmanagerassignment.webService.PostService

class PostRepository(
    private val postDatabase: PostDatabase,
    private val postService: PostService,
    private val applicationContext: Context
) {
    private val postLiveData = MutableLiveData<List<PostModel>>()

    val post: LiveData<List<PostModel>>
        get() = postLiveData

//    suspend fun getPost() {
//        if (Network.isInternetAvailable(applicationContext)){
//            val data = postService.webService.getPost()
//            if (data.body() != null) {
//                postDatabase.contactDao().insertAllPost(data.body()!!)
//                postLiveData.postValue(data.body())
//            }
//        }else{
//            val data = postDatabase.contactDao().getPost()
//            postLiveData.postValue(data)
//        }
//    }

    suspend fun getPostDataFromApi(){
        val data = postService.webService.getPost()
        if (data.body() != null) {
//            postDatabase.contactDao().insertAllPost(data.body()!!)
            postLiveData.postValue(data.body())
        }
    }

    suspend fun saveToDatabase(){
        val data = postService.webService.getPost()
        if (data.body() != null){
            postDatabase.contactDao().insertAllPost(data.body()!!)
        }
    }

}