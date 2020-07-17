package kashyap.in.androidbestpractices.base.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkBroadcast extends BroadcastReceiver {

    NetworkChangeListener networkChangeListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (networkChangeListener != null) {
            networkChangeListener.onNetworkChanged();
        }
    }

    public void setSharedListener(NetworkChangeListener networkChangeListener) {
        this.networkChangeListener = networkChangeListener;
    }

    public interface NetworkChangeListener {
        void onNetworkChanged();
    }
}
