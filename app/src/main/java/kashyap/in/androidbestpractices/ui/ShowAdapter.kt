package kashyap.`in`.androidbestpractices.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kashyap.`in`.androidbestpractices.databinding.ItemRepoDetailBinding

class ShowAdapter(val clickListener: ShowClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsToDisplay>() {
        override fun areItemsTheSame(oldItem: ItemsToDisplay, newItem: ItemsToDisplay): Boolean {
            return oldItem.getShowId() == newItem.getShowId()
        }

        override fun areContentsTheSame(oldItem: ItemsToDisplay, newItem: ItemsToDisplay): Boolean {
            return oldItem.getShowName() == newItem.getShowName()
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowViewHolder(ItemRepoDetailBinding.inflate(LayoutInflater.from(parent.context)))
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

    fun <T : ItemsToDisplay> submitList(list: List<T>) {
        differ.submitList(list)
    }

}

class ShowViewHolder(private var binding: ItemRepoDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemsToDisplay: ItemsToDisplay, clickListener: ShowClickListener) {
        binding.show = itemsToDisplay
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class ShowClickListener(val clickListener: (itemsToDisplay: ItemsToDisplay) -> Unit) {
    fun onClick(itemsToDisplay: ItemsToDisplay) = clickListener(itemsToDisplay)
}
