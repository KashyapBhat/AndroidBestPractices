package kashyap.`in`.androidbestpractices.base.recyclerview

import android.app.backup.BackupManager.dataChanged
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kashyap.`in`.androidbestpractices.BR

abstract class MyBaseAdapter(
    private val clickListener: RVItemClickListener,
    val dataSetChanged: DataSetChanged?
) :
    RecyclerView.Adapter<MyBaseAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return MyViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        dataSetChanged?.onDataChange(differ.currentList[position], position, itemCount)
        holder.bind(differ.currentList[position], clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position, differ.currentList[position])
    }

    protected abstract fun getLayoutIdForPosition(position: Int, dataAtThatPostion: Any): Int

    fun submitList(list: List<Any>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?, clickListener: RVItemClickListener) {
            binding.setVariable(BR.obj, obj)
            binding.setVariable(BR.clickListener, clickListener)
            binding.executePendingBindings()
        }
    }

    class RVItemClickListener(val clickListener: (user: Any) -> Unit) {
        fun onClick(user: Any) = clickListener(user)
    }

    interface DataSetChanged {
        fun onDataChange(changedData: Any, position: Int, totalSize: Int)
    }
}