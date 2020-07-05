package kashyap.`in`.androidbestpractices.data.models

import com.google.gson.annotations.SerializedName
import kashyap.`in`.androidbestpractices.data.entities.UserDetailsEntity

data class GoRestResponse(
    @SerializedName("_meta")
    var meta: Meta? = null,
    @SerializedName("result")
    var userDetails: List<UserDetails>? = null
)

data class Meta(
    @SerializedName("code")
    var code: Int? = 0,
    @SerializedName("currentPage")
    var currentPage: Int? = 0,
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("pageCount")
    var pageCount: Int? = 0,
    @SerializedName("perPage")
    var perPage: Int? = 0,
    @SerializedName("rateLimit")
    var rateLimit: RateLimit? = null,
    @SerializedName("success")
    var success: Boolean? = false,
    @SerializedName("totalCount")
    var totalCount: Int? = 0
)

data class UserDetails(
    @SerializedName("address")
    var address: String? = "",
    @SerializedName("dob")
    var dob: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("first_name")
    var firstName: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("last_name")
    var lastName: String? = "",
    @SerializedName("_links")
    var links: Links? = null,
    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("website")
    var website: String? = ""
)

data class RateLimit(
    @SerializedName("limit")
    var limit: Int? = 0,
    @SerializedName("remaining")
    var remaining: Int? = 0,
    @SerializedName("reset")
    var reset: Int? = 0
)

data class Links(
    @SerializedName("avatar")
    var avatar: Avatar? = null,
    @SerializedName("edit")
    var edit: Edit? = null,
    @SerializedName("self")
    var self: Self? = null
)

data class Avatar(
    @SerializedName("href")
    var href: String? = ""
)

data class Edit(
    @SerializedName("href")
    var href: String? = ""
)

data class Self(
    @SerializedName("href")
    var href: String? = ""
)

fun GoRestResponse.asUserEntities(): Array<UserDetailsEntity> {
    return userDetails?.map {
        UserDetailsEntity(
            id = it.id ?: "",
            address = it.address,
            dob = it.dob,
            email = it.email,
            first_name = it.firstName,
            gender = it.gender,
            last_name = it.lastName,
            phone = it.phone,
            status = it.status,
            website = it.website
        )
    }?.toTypedArray() ?: arrayOf()
}