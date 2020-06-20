package kashyap.`in`.androidbestpractices.network

import android.content.Context
import androidx.lifecycle.LiveData
import kashyap.`in`.androidbestpractices.data.MyDatabase
import kashyap.`in`.androidbestpractices.data.models.GithubRepoDetails
import kashyap.`in`.androidbestpractices.injection.ApplicationContext
import kashyap.`in`.androidbestpractices.injection.ApplicationScope
import kashyap.`in`.androidbestpractices.network.webservice.ApiService
import kashyap.`in`.androidbestpractices.network.webservice.NetworkBoundResource
import kashyap.`in`.androidbestpractices.network.webservice.Response
import kashyap.`in`.androidbestpractices.utils.isNetworkOnline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ApplicationScope
class MyRepository @Inject constructor(
    val local: MyDatabase,
    val remote: ApiService,
    @ApplicationContext val context: Context
) {

    suspend fun loadRepoDetails(): LiveData<Response<List<GithubRepoDetails>>> {
        return object : NetworkBoundResource<List<GithubRepoDetails>, List<GithubRepoDetails>>() {
            override fun saveCallResult(item: List<GithubRepoDetails>) {
                CoroutineScope(Dispatchers.IO).launch {
                    local.githubRepoDetailsDao().insertAll(*item.toTypedArray())
                }
            }

            override fun loadFromDb(): LiveData<List<GithubRepoDetails>> {
                return local.githubRepoDetailsDao().getAllRepoDetails()
            }

            override suspend fun createCall(): List<GithubRepoDetails> {
                return remote.fetchRepoDetails()
            }

            override fun shouldFetch(data: List<GithubRepoDetails>?): Boolean {
                return isNetworkOnline(context = context)
            }
        }.asLiveData()
    }

}

