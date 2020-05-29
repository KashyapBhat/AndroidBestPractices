package kashyap.`in`.androidbestpractices.common.constants

enum class ApiStatus { SUCCESS, ERROR, LOADING }

enum class ErrorCodes(val code: Int, val message: String) {
    SocketTimeOut(-1, "Timeout"),
    UnAuthorised(401, "Unauthorised"),
    NotFound(404, "Not found")
}