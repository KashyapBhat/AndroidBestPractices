package kashyap.`in`.androidbestpractices.network

import android.content.Context
import androidx.lifecycle.LiveData
import kashyap.`in`.androidbestpractices.data.MyDatabase
import kashyap.`in`.androidbestpractices.injection.ApplicationContext
import kashyap.`in`.androidbestpractices.injection.ApplicationScope
import kashyap.`in`.androidbestpractices.network.webservice.ApiService
import kashyap.`in`.androidbestpractices.network.webservice.NetworkBoundResource
import kashyap.`in`.androidbestpractices.network.webservice.Response
import kashyap.`in`.androidbestpractices.common.utils.isNetworkOnline
import kashyap.`in`.androidbestpractices.data.entities.UserDetailsEntity
import kashyap.`in`.androidbestpractices.data.entities.asDomainModel
import kashyap.`in`.androidbestpractices.data.models.GoRestResponse
import kashyap.`in`.androidbestpractices.data.models.UserDetails
import kashyap.`in`.androidbestpractices.data.models.asUserEntities
import kashyap.`in`.androidbestpractices.ui.repodetails.User
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

    suspend fun loadRepoDetails(): LiveData<Response<List<User>>> {
        return object :
            NetworkBoundResource<List<User>, GoRestResponse>() {
            override fun saveCallResult(item: GoRestResponse) {
                CoroutineScope(Dispatchers.IO).launch {
                    local.userDetailsDao().insertAll(*item.asUserEntities())
                }
            }

            override fun loadFromDb(): LiveData<List<User>> {
                return local.userDetailsDao().getAllUserDetails().asDomainModel()
            }

            override suspend fun createCall(): GoRestResponse {
                return remote.fetchRepoDetails()
            }

            override fun shouldFetch(): Boolean {
                return true
            }
        }.asLiveData()
    }

}

