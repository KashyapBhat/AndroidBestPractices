package kashyap.`in`.androidbestpractices.data.models

import android.os.Parcelable
import androidx.room.Entity
import kashyap.`in`.androidbestpractices.common.constants.ID
import kashyap.`in`.androidbestpractices.common.constants.REPO_DETAILS_TABLE
import kashyap.`in`.androidbestpractices.ui.repodetails.RepoItemsToDisplay
import kotlinx.android.parcel.Parcelize

@Entity(tableName = REPO_DETAILS_TABLE, primaryKeys = [ID])
@Parcelize
data class GithubRepoDetails(
    var archive_url: String? = "",
    var assignees_url: String? = "",
    var blobs_url: String? = "",
    var branches_url: String? = "",
    var collaborators_url: String? = "",
    var comments_url: String? = "",
    var commits_url: String? = "",
    var compare_url: String? = "",
    var contents_url: String? = "",
    var contributors_url: String? = "",
    var deployments_url: String? = "",
    var description: String? = "",
    var downloads_url: String? = "",
    var events_url: String? = "",
    var fork: Boolean? = false,
    var forks_url: String? = "",
    var full_name: String? = "",
    var git_commits_url: String? = "",
    var git_refs_url: String? = "",
    var git_tags_url: String? = "",
    var hooks_url: String? = "",
    var html_url: String? = "",
    var id: Int? = 0,
    var issue_comment_url: String? = "",
    var issue_events_url: String? = "",
    var issues_url: String? = "",
    var keys_url: String? = "",
    var labels_url: String? = "",
    var languages_url: String? = "",
    var merges_url: String? = "",
    var milestones_url: String? = "",
    var name: String? = "",
    var node_id: String? = "",
    var notifications_url: String? = "",
    var ownerDetails: OwnerDetails? = null,
    var private: Boolean? = null,
    var pulls_url: String? = "",
    var releases_url: String? = "",
    var stargazers_url: String? = "",
    var statuses_url: String? = "",
    var subscribers_url: String? = "",
    var subscription_url: String? = "",
    var tags_url: String? = "",
    var teams_url: String? = "",
    var trees_url: String? = "",
    var url: String? = ""
) : Parcelable,
    RepoItemsToDisplay {
    override fun getShowId(): String {
        return id?.toString() ?: ""
    }

    override fun getShowName(): String {
        return name ?: ""
    }

    override fun getFullName(): String {
        return full_name ?: ""
    }

    override fun getImageUrl(): String {
        return ownerDetails?.avatar_url ?: ""
    }

    override fun getLinkUrl(): String {
        return ownerDetails?.url ?: ""
    }
}

@Parcelize
data class OwnerDetails(
    var avatar_url: String? = "",
    var events_url: String? = "",
    var followers_url: String? = "",
    var following_url: String? = "",
    var gists_url: String? = "",
    var gravatar_id: String? = "",
    var html_url: String? = "",
    var id: Int? = 0,
    var login: String? = "",
    var node_id: String? = "",
    var organizations_url: String? = "",
    var received_events_url: String? = "",
    var repos_url: String? = "",
    var site_admin: Boolean? = false,
    var starred_url: String? = "",
    var subscriptions_url: String? = "",
    var type: String? = "",
    var url: String? = ""
) : Parcelable