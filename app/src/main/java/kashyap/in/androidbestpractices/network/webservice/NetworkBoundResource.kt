package kashyap.`in`.androidbestpractices.network.webservice

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    protected abstract suspend fun createCall(): RequestType

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    private val responseHandler =
        ResponseHandler()

    fun asLiveData(): LiveData<Response<ResultType>> {
        return liveData {
            val disposable = emitSource(loadFromDb().map { responseHandler.handleLoading(it) })
            try {
                val apiResponse = createCall()
                apiResponse.let {
                    disposable.dispose()
                    saveCallResult(apiResponse)
                    emitSource(
                        loadFromDb().map {
                            responseHandler.handleSuccess(it)
                        }
                    )
                }
            } catch (e: Exception) {
                emitSource(loadFromDb().map { responseHandler.handleException(e, it) })
            }
        }
    }

}