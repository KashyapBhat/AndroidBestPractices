package kashyap.`in`.androidbestpractices.network.webservice

import kashyap.`in`.androidbestpractices.data.models.GithubRepoDetails
import retrofit2.http.GET

interface ApiService {

    @GET("repositories")
    suspend fun fetchRepoDetails(): List<GithubRepoDetails>
}