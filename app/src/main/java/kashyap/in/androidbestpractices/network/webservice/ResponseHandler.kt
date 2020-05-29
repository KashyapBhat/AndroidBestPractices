package kashyap.`in`.androidbestpractices.network.webservice

import kashyap.`in`.androidbestpractices.common.constants.ApiStatus
import kashyap.`in`.androidbestpractices.common.constants.ErrorCodes
import retrofit2.HttpException
import java.net.SocketTimeoutException

data class Response<out T>(val status: ApiStatus, val data: T?, val message: String?)

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Response<T> {
        return Response(
            ApiStatus.SUCCESS,
            data,
            null
        )
    }

    fun <T : Any> handleLoading(data: T): Response<T> {
        return Response(
            ApiStatus.LOADING,
            data,
            "loading"
        )
    }

    fun <T : Any> handleException(e: Exception, data: T?): Response<T> {
        e.printStackTrace()
        return when (e) {
            is HttpException ->
                Response(
                    ApiStatus.ERROR,
                    data,
                    getErrorMessage(e.code())
                )
            is SocketTimeoutException ->
                Response(
                    ApiStatus.ERROR,
                    data,
                    getErrorMessage(ErrorCodes.SocketTimeOut.code)
                )
            else -> Response(
                ApiStatus.ERROR,
                data,
                getErrorMessage(Int.MAX_VALUE)
            )
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> ErrorCodes.SocketTimeOut.message
            ErrorCodes.UnAuthorised.code -> ErrorCodes.UnAuthorised.message
            ErrorCodes.NotFound.code -> ErrorCodes.NotFound.message
            else -> "Something went wrong"
        }
    }
}
