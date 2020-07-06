package kashyap.`in`.androidbestpractices.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kashyap.`in`.androidbestpractices.MyApplication
import kashyap.`in`.androidbestpractices.R
import kashyap.`in`.androidbestpractices.base.recyclerview.MyBaseAdapter
import kashyap.`in`.androidbestpractices.base.recyclerview.SingleLayoutAdapter
import kashyap.`in`.androidbestpractices.databinding.FragmentUserDetailsBinding
import javax.inject.Inject

class UserDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UserDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        (activity?.applicationContext as MyApplication).component?.inject(this)
        binding.userDetails = viewModel.userDetailsListData
        val userAdapter = SingleLayoutAdapter(
            R.layout.item_user_detail,
            MyBaseAdapter.RVItemClickListener { user ->
                if (user is User)
                    showId(user)
            })
//        val showAdapter = UserDetailsAdapter(ShowClickListener { user -> showId(user) })
        binding.rvDetails.adapter = userAdapter
        return binding.root
    }

    private fun showId(user: User) {
        Toast.makeText(context, "User Id=" + user.id, Toast.LENGTH_SHORT).show()
    }
}