package kashyap.`in`.androidbestpractices.base

import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kashyap.`in`.androidbestpractices.base.listeners.NetworkBroadcast
import kashyap.`in`.androidbestpractices.utils.hideKeyboard
import kashyap.`in`.androidbestpractices.utils.isNetworkOnline
import kashyap.`in`.androidbestpractices.utils.showKeyboard

abstract class BaseActivity : AppCompatActivity(), NetworkBroadcast.NetworkChangeListener {

    lateinit var networkChangeReceiver: NetworkBroadcast
    var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onNetworkChanged()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    private fun registerReceiver() {
        networkChangeReceiver = NetworkBroadcast()
        networkChangeReceiver.setSharedListener(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    fun hideKeyboard() {
        hideKeyboard(currentFocus, this)
    }

    fun showKeyboard(view: View?) {
        showKeyboard(view, this)
    }

    override fun onNetworkChanged() {
        isNetworkAvailable = isNetworkOnline(this)
        if (isNetworkAvailable) {
            // Hide internet not available
        } else {
            // Show internet not available
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    fun runNetworkDependentTask(onNetworkAvailable: Runnable, onNetworkUnavailable: Runnable?) {
        when (isNetworkOnline(this)) {
            true -> onNetworkAvailable.run()
            false -> if (onNetworkUnavailable != null) {
                onNetworkUnavailable.run()
            } else {
                Toast.makeText(this, "Please check your internet connection.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
