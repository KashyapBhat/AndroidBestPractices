package kashyap.`in`.androidbestpractices.network.webservice

import retrofit2.http.GET

interface ApiService {

    @GET("")
    suspend fun getSomething(): String
}