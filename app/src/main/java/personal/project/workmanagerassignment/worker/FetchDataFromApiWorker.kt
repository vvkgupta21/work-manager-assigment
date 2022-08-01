package personal.project.workmanagerassignment.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import personal.project.workmanagerassignment.WmApplication

class FetchDataFromApiWorker(private val context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        try {
            val repository = (context as WmApplication).postRepository
            repository.getPostDataFromApi()
            Log.d("one time request", "FetchDataFromApiWorker")

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}