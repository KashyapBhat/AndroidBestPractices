package kashyap.`in`.androidbestpractices.network.webservice

import kashyap.`in`.androidbestpractices.data.models.GoRestResponse
import retrofit2.http.GET

interface ApiService {

    @GET("public-api/users")
    suspend fun fetchRepoDetails(): GoRestResponse
}