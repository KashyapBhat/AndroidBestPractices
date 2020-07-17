package kashyap.`in`.androidbestpractices.network.webservice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    protected abstract suspend fun createCall(): RequestType

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(): Boolean

    private val responseHandler =
        ResponseHandler()

    suspend fun asMutableLiveData(): LiveData<Response<ResultType>> {
        return MediatorLiveData<Response<ResultType>>().let { data ->
            data.addSource(loadFromDb()) { responseHandler.handleLoading(it) }
            try {
                val apiResponse = createCall()
                apiResponse.let {
                    saveCallResult(apiResponse)
                    data.addSource(loadFromDb()) {
                        responseHandler.handleSuccess(it)
                    }
                }
            } catch (e: Exception) {
                data.addSource(loadFromDb()) { responseHandler.handleException(e, it) }
            }
            data
        }
    }

    fun asLiveData(): LiveData<Response<ResultType>> {
        return liveData {
            if (shouldFetch()) {
                val disposable = emitSource(loadFromDb().map { responseHandler.handleLoading(it) })
                try {
                    val apiResponse = createCall()
                    apiResponse.let {
                        disposable.dispose()
                        saveCallResult(apiResponse)
                        emitSource(
                            loadFromDb().map {
                                Log.d("NetworkBoundResource ", "Success: $it")
                                responseHandler.handleSuccess(it)
                            }
                        )
                    }
                } catch (e: Exception) {
                    emitSource(loadFromDb().map {
                        Log.d("NetworkBoundResource", "Exception: $it")
                        responseHandler.handleException(e, it)
                    })
                }
            } else emitSource(loadFromDb().map {
                Log.d("NetworkBoundResource", "No Internet: $it")
                responseHandler.handleSuccess(it)
            })
        }
    }

}