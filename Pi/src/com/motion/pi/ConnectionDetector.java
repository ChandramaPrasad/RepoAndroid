package com.motion.pi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by BABu on 30/3/15.
 */
public class ConnectionDetector {
    private Context _cContext;

    public ConnectionDetector(Context context){
        this._cContext=context;
    }

    public boolean isConnectingToInternet(){
        ConnectivityManager conm=(ConnectivityManager) _cContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conm!=null){
            NetworkInfo[] info=conm.getAllNetworkInfo();
            if(info !=null){
                for (int i=0;i<info.length;i++){
                    if(info[i].getState()==NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }

        }
        return false;
    }
}
