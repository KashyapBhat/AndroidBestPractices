package kashyap.`in`.androidbestpractices.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager

fun isNetworkOnline(context: Context?): Boolean {
    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetwork: NetworkInfo? = cm?.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun showKeyboard(view: View?, context: Context?) {
    if (view != null) {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        view.requestFocus()
        inputMethodManager?.showSoftInput(view, 0)
    }
}

fun hideKeyboard(view: View?, context: Context?) {
    if (view != null) {
        val imm =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}