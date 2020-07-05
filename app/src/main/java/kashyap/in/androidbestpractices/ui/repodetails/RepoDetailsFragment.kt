package kashyap.`in`.androidbestpractices.ui.repodetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kashyap.`in`.androidbestpractices.MyApplication
import kashyap.`in`.androidbestpractices.data.MyDatabase
import kashyap.`in`.androidbestpractices.databinding.FragmentRepoDetailsBinding
import javax.inject.Inject

class RepoDetailsFragment : Fragment() {

    @Inject
    @JvmField
    public var myDatabase: MyDatabase? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RepoDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRepoDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        (activity?.applicationContext as MyApplication).component?.inject(this)
        binding.repoDetails = viewModel.repoDetailsListData
        val showAdapter = RepoDetailsAdapter(ShowClickListener { show -> openWebsite(show) })
        binding.rvDetails.adapter = showAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val someValue = myDatabase?.userDetailsDao()?.getAllUserDetails()?.value
        Log.d("MyDatabase", "" + someValue.toString())
    }

    private fun openWebsite(show: User) {
        var url = show.id ?: ""
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://$url";
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}