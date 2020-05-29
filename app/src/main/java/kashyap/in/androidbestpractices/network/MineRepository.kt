package kashyap.`in`.androidbestpractices.network

import androidx.lifecycle.LiveData
import kashyap.`in`.androidbestpractices.data.MyDatabase
import kashyap.`in`.androidbestpractices.injection.ApplicationScope
import kashyap.`in`.androidbestpractices.network.webservice.ApiService
import kashyap.`in`.androidbestpractices.network.webservice.NetworkBoundResource
import kashyap.`in`.androidbestpractices.network.webservice.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ApplicationScope
class MyRepository @Inject constructor(val local: MyDatabase, val remote: ApiService) {

    suspend fun loadMovies(flag: Int): LiveData<Response<List<String>>> {
        return object : NetworkBoundResource<List<String>, String>() {
            override fun saveCallResult(item: String) {
                CoroutineScope(Dispatchers.IO).launch {
                    local.someDao().insertAll(item)
                }
            }

            override fun loadFromDb(): LiveData<List<String>> {
                return local.someDao().getDao()
            }

            override suspend fun createCall(): String {
                return remote.getSomething()
            }

            override fun shouldFetch(data: List<String>?): Boolean {
                return true //TODO: return isOnline
            }
        }.asLiveData()
    }

}

