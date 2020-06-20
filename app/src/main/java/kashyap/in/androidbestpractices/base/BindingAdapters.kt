package kashyap.`in`.androidbestpractices.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kashyap.`in`.androidbestpractices.R
import kashyap.`in`.androidbestpractices.ui.repodetails.RepoItemsToDisplay
import kashyap.`in`.androidbestpractices.ui.repodetails.ShowAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun <T : RepoItemsToDisplay> bindRecyclerView(recyclerView: RecyclerView, showList: List<T>?) =
    showList?.let { (recyclerView.adapter as ShowAdapter).submitList(it) }
