package kashyap.`in`.androidbestpractices.ui.userdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kashyap.`in`.androidbestpractices.databinding.ItemUserDetailBinding

class UserDetailsAdapter(private val clickListener: ShowClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.first_name == newItem.first_name
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowViewHolder(ItemUserDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowViewHolder -> holder.bind(differ.currentList[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<User>) {
        differ.submitList(list)
    }

}

class ShowViewHolder(private var binding: ItemUserDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, clickListener: ShowClickListener) {
        binding.user = user
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class ShowClickListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}
