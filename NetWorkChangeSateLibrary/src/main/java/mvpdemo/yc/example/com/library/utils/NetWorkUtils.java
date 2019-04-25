package mvpdemo.yc.example.com.library.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import mvpdemo.yc.example.com.library.NetWorkManager;
import mvpdemo.yc.example.com.library.type.NetType;

/**
 * Created by Yuchao on 2019/3/27.
 */

public class NetWorkUtils {

    @SuppressLint("MissingPermission")
    public static boolean isNetWorkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) NetWorkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return false;
        }
        NetworkInfo[] info = connMgr.getAllNetworkInfo();
        if (info != null) {
            for (NetworkInfo anInfo : info) {
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    public static NetType getNetType() {
        ConnectivityManager connMgr = (ConnectivityManager) NetWorkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return NetType.NONE;
        }

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }

        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }


        return NetType.NONE;
    }


    public static void openSetting(Context context, int requestCode) {
        Intent intent = new Intent("/");
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        intent.setAction("android.intent.action.View");
        ((Activity) context).startActivityForResult(intent, requestCode);

    }


}
