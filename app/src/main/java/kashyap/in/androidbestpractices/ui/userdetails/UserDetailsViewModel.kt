package kashyap.`in`.androidbestpractices.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kashyap.`in`.androidbestpractices.network.MyRepository
import kashyap.`in`.androidbestpractices.network.webservice.Response
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    val userDetailsListData: LiveData<Response<List<User>>> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(repository.loadUserDetails())
        }
}