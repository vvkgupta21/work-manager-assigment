package personal.project.workmanagerassignment.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import personal.project.workmanagerassignment.WmApplication

class PostWorker(private var context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.d("Worker Class", "doWork")
        val repository = (context as WmApplication).postRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getPostDataFromApi()
        }
        return Result.success()
    }
}