package kashyap.`in`.androidbestpractices.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kashyap.`in`.androidbestpractices.data.models.GithubRepoDetails
import kashyap.`in`.androidbestpractices.network.MyRepository
import kashyap.`in`.androidbestpractices.network.webservice.Response
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    val repoDetailsListData: LiveData<Response<List<GithubRepoDetails>>> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(repository.loadRepoDetails())
        }
}