package kashyap.`in`.androidbestpractices.base.listeners

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkBroadcast : BroadcastReceiver() {
    var networkChangeListener: NetworkChangeListener? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (networkChangeListener != null) {
            networkChangeListener!!.onNetworkChanged()
        }
    }

    fun setSharedListener(networkChangeListener: NetworkChangeListener?) {
        this.networkChangeListener = networkChangeListener
    }

    interface NetworkChangeListener {
        fun onNetworkChanged()
    }
}