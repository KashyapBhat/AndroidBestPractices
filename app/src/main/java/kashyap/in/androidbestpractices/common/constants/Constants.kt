package kashyap.`in`.androidbestpractices.common.constants

enum class ApiStatus { SUCCESS, ERROR, LOADING }

enum class ErrorCodes(val code: Int, val message: String) {
    SocketTimeOut(-1, "Timeout"),
    UnAuthorised(401, "Unauthorised"),
    NotFound(404, "Not found")
}

const val AUTH_KEY = "access-token"
const val FORMAT = "_format"

const val CONNECT_TIMEOUT: Long = 60 * 1000
const val READ_TIMEOUT: Long = 60 * 1000
const val WRITE_TIMEOUT: Long = 60 * 1000