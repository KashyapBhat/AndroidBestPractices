package kashyap.`in`.androidbestpractices.ui.repodetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kashyap.`in`.androidbestpractices.databinding.ItemRepoDetailBinding

class ShowAdapter(val clickListener: ShowClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoItemsToDisplay>() {
        override fun areItemsTheSame(oldItem: RepoItemsToDisplay, newItem: RepoItemsToDisplay): Boolean {
            return oldItem.getShowId() == newItem.getShowId()
        }

        override fun areContentsTheSame(oldItem: RepoItemsToDisplay, newItem: RepoItemsToDisplay): Boolean {
            return oldItem.getShowName() == newItem.getShowName()
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowViewHolder(
            ItemRepoDetailBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowViewHolder -> {
                holder.bind(differ.currentList[position], clickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun <T : RepoItemsToDisplay> submitList(list: List<T>) {
        differ.submitList(list)
    }

}

class ShowViewHolder(private var binding: ItemRepoDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repoItemsToDisplay: RepoItemsToDisplay, clickListener: ShowClickListener) {
        binding.show = repoItemsToDisplay
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class ShowClickListener(val clickListener: (repoItemsToDisplay: RepoItemsToDisplay) -> Unit) {
    fun onClick(repoItemsToDisplay: RepoItemsToDisplay) = clickListener(repoItemsToDisplay)
}
