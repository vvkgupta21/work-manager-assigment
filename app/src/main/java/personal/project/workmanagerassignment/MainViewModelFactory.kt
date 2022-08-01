package personal.project.workmanagerassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import personal.project.workmanagerassignment.repository.PostRepository
import personal.project.workmanagerassignment.viewModel.MainViewModel

class MainViewModelFactory(var repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}