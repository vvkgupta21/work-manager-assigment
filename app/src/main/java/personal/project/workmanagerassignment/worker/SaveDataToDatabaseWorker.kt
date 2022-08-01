package personal.project.workmanagerassignment.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import personal.project.workmanagerassignment.WmApplication

class SaveDataToDatabaseWorker(private val context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        try {
            val repository = (context as WmApplication).postRepository
            repository.saveToDatabase()
            Log.d("one time request", "SaveDataToDatabaseWorker")
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}