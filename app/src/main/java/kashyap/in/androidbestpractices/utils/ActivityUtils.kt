package kashyap.`in`.androidbestpractices.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager

fun isNetworkOnline(context: Context?): Boolean {
    var status = false
    if (context != null) {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connManager != null) {
            var mWifi: NetworkInfo? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                mWifi = connManager.getNetworkInfo(NetworkCapabilities.TRANSPORT_WIFI)
            } else {
                mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            }
            if (mWifi != null && mWifi.isConnected) {
                return true
            }
            try {
                var netInfo: NetworkInfo? = connManager.getNetworkInfo(0)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                    status = true
                } else {
                    netInfo = connManager.getNetworkInfo(1)
                    if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED)
                        status = true
                }
            } catch (e: Exception) {
                return false
            }

        }
    }
    return status
}

fun showKeyboard(view: View?, context: Context) {
    if (view != null) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager?.showSoftInput(view, 0)
    }
}

fun hideKeyboard(view: View?, context: Context) {
    if (view != null) {
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}