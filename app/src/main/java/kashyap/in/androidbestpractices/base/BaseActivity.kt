package kashyap.`in`.androidbestpractices.base

import android.app.Dialog
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kashyap.`in`.androidbestpractices.R
import kashyap.`in`.androidbestpractices.base.listeners.NetworkBroadcast
import kashyap.`in`.androidbestpractices.common.utils.hideKeyboard
import kashyap.`in`.androidbestpractices.common.utils.isNetworkOnline
import kashyap.`in`.androidbestpractices.common.utils.showKeyboard

abstract class BaseActivity : AppCompatActivity(), NetworkBroadcast.NetworkChangeListener {

    lateinit var networkChangeReceiver: NetworkBroadcast
    var isNetworkAvailable: Boolean = false
    var loadingDialog: Dialog? = null

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

    fun showLoadingDialog() {
        if (loadingDialog == null)
            loadingDialog = Dialog(this, R.style.FullScreenDialog)
        loadingDialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        loadingDialog?.setContentView(R.layout.loading)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.setCancelable(false)
        loadingDialog?.show()

    }

    fun hideLoadingDialog() {
        if (loadingDialog?.isShowing == true && !isFinishing) {
            loadingDialog?.dismiss()
        }
    }

    override fun onNetworkChanged() {
        isNetworkAvailable = isNetworkOnline(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
        hideLoadingDialog()
    }

    fun runNetworkDependentTask(onNetworkAvailable: Runnable, onNetworkUnavailable: Runnable?) {
        when (isNetworkAvailable) {
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
