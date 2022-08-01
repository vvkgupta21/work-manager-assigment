package personal.project.workmanagerassignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    suspend fun getPost(): List<PostModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(post: List<PostModel>)

}