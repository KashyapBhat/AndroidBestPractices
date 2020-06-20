package kashyap.`in`.androidbestpractices

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kashyap.`in`.androidbestpractices.base.BaseActivity
import kashyap.`in`.androidbestpractices.ui.RepoDetailsFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }

    private fun openFragment() {
        val fragment: Fragment = RepoDetailsFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fr_container, fragment).commit()
    }
}
