package kashyap.`in`.androidbestpractices.ui.repodetails

interface RepoItemsToDisplay {
    fun getShowId(): String
    fun getShowName(): String
    fun getFullName(): String
    fun getImageUrl(): String
    fun getLinkUrl(): String
}