package kashyap.`in`.androidbestpractices.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kashyap.`in`.androidbestpractices.common.constants.ID
import kashyap.`in`.androidbestpractices.common.constants.REPO_DETAILS_TABLE
import kashyap.`in`.androidbestpractices.data.models.GithubRepoDetails

@Dao
interface GithubRepoDetailsDao : BaseDao<GithubRepoDetails> {

    @Query("SELECT * FROM $REPO_DETAILS_TABLE")
    fun getAllRepoDetails(): LiveData<List<GithubRepoDetails>>

    @Query("SELECT * FROM $REPO_DETAILS_TABLE WHERE $ID = :userId")
    fun getRepoDetailsWrtUserId(userId: Int): LiveData<GithubRepoDetails>
}