package personal.project.workmanagerassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import personal.project.workmanagerassignment.repository.PostRepository
import personal.project.workmanagerassignment.room.PostModel

class MainViewModel(private val repo: PostRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repo.getPostDataFromApi()
        }
    }

    val post: LiveData<List<PostModel>>
        get() = repo.post

}