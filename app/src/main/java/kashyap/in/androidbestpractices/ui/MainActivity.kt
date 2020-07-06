package kashyap.`in`.androidbestpractices.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kashyap.`in`.androidbestpractices.R
import kashyap.`in`.androidbestpractices.base.BaseActivity
import kashyap.`in`.androidbestpractices.ui.userdetails.UserDetailsFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }

    private fun openFragment() {
        val fragment: Fragment =
            UserDetailsFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fr_container, fragment).commit()
    }
}
