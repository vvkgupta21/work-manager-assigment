package personal.project.workmanagerassignment

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import personal.project.workmanagerassignment.repository.PostRepository
import personal.project.workmanagerassignment.room.PostDatabase
import personal.project.workmanagerassignment.webService.PostService
import personal.project.workmanagerassignment.worker.PostWorker
import java.util.concurrent.TimeUnit

class WmApplication: Application() {

    lateinit var postRepository: PostRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
//        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(PostWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {
        val database = PostDatabase.getDataBase(applicationContext)
        postRepository = PostRepository(database, PostService, applicationContext)
    }
}