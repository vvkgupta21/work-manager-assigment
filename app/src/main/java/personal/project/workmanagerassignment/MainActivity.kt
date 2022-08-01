package personal.project.workmanagerassignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import personal.project.workmanagerassignment.databinding.ActivityMainBinding
import personal.project.workmanagerassignment.viewModel.MainViewModel
import personal.project.workmanagerassignment.worker.FetchDataFromApiWorker
import personal.project.workmanagerassignment.worker.SaveDataToDatabaseWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = (application as WmApplication).postRepository
        mainViewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(repository)
            )[MainViewModel::class.java]
//        fetchData()

        binding.btn.setOnClickListener {
            oneTimeRequest()
        }
        binding.btn2.setOnClickListener {
            setPeriodicWorkRequest()
        }

    }

    //Task 1
    private fun oneTimeRequest(){
        val workManager = WorkManager.getInstance(applicationContext)
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val apiWorker = OneTimeWorkRequest.Builder(FetchDataFromApiWorker::class.java)
            .setConstraints(constraint)
            .build()

        val saveToDatabaseWorker = OneTimeWorkRequest.Builder(SaveDataToDatabaseWorker::class.java)
            .build()

        workManager.beginWith(apiWorker)
            .then(saveToDatabaseWorker)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(apiWorker.id).observe(this, Observer {
            if (it.state.isFinished){
                Log.d("state check", "oneTimeRequest: $it")
            }
        })

    }

    //Task 2
    private fun setPeriodicWorkRequest(){
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(FetchDataFromApiWorker::class.java, 15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun fetchData() {
//        val list = ArrayList<PostModel>()
//        mainViewModel.post.observe(this, Observer {
//            list.clear()
//            list.addAll(it)
//            Log.d(ContentValues.TAG, "onCreate: ${it.size}")
//            binding.txt.text = it.size.toString()
//            if (binding.recyclerview.adapter == null) {
//                binding.recyclerview.adapter = AdapterRoom(list = list)
//            } else {
//                binding.recyclerview.adapter?.notifyDataSetChanged()
//            }
//        })
//    }



}

