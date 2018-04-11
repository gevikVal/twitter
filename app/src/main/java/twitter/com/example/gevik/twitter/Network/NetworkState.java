package twitter.com.example.gevik.twitter.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gvalijani on 04/11/18.
 */

public class NetworkState {
    private Context context;

    public NetworkState(Context context) {
        this.context = context;
    }

    public boolean isConnectedOrConnecting() {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        if (isConnected) {
            return true;
        }

        return false;
    }
}
