package kashyap.`in`.androidbestpractices.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kashyap.`in`.androidbestpractices.common.constants.ID
import kashyap.`in`.androidbestpractices.common.constants.USER_DETAILS_TABLE
import kashyap.`in`.androidbestpractices.data.entities.UserDetailsEntity

@Dao
interface UserDetailsDao : BaseDao<UserDetailsEntity> {

    @Query("SELECT * FROM $USER_DETAILS_TABLE")
    fun getAllUserDetails(): LiveData<List<UserDetailsEntity>>

    @Query("SELECT * FROM $USER_DETAILS_TABLE WHERE $ID = :userId")
    fun getUserDetailsWrtId(userId: Int): LiveData<UserDetailsEntity>
}