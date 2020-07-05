package kashyap.`in`.androidbestpractices.data.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import kashyap.`in`.androidbestpractices.common.constants.ID
import kashyap.`in`.androidbestpractices.common.constants.USER_DETAILS_TABLE
import kashyap.`in`.androidbestpractices.ui.repodetails.User

@Entity(tableName = USER_DETAILS_TABLE, primaryKeys = [ID])
data class UserDetailsEntity(
    var address: String? = "",
    var dob: String? = "",
    var email: String? = "",
    var first_name: String? = "",
    var gender: String? = "",
    var id: String = "",
    var last_name: String? = "",
    var phone: String? = "",
    var status: String? = "",
    var website: String? = ""
)

fun List<UserDetailsEntity>.asDomainModel(): List<User> {
    return map {
        User(
            id = it.id,
            first_name = it.first_name,
            last_name = it.last_name,
            gender = it.gender
        )
    }
}

fun LiveData<List<UserDetailsEntity>>.asDomainModel(): LiveData<List<User>> =
    Transformations.map(this) { it.asDomainModel() }